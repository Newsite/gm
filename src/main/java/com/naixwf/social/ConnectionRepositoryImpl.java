package com.naixwf.social;

import com.naixwf.social.dao.AccountDao;
import com.naixwf.social.dao.SocialConnectionDao;
import com.naixwf.social.domain.Account;
import com.naixwf.social.domain.SocialConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.social.connect.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: wangfei
 * Date: 13-4-18
 * Time: 下午5:06
 * http://harmonicdevelopment.tumblr.com/post/13613051804/adding-spring-social-to-a-spring-mvc-and-spring
 * This is the request scoped bean for logged in users, for working with a single user’s Spring Social connection information.
 * This bean will be instantiated by the UsersConnectionRepository and will have its dependencies passed to its constructor.
 */

public class ConnectionRepositoryImpl implements ConnectionRepository {
    @SuppressWarnings("unused")
    private static final Logger logger = LoggerFactory.getLogger(ConnectionRepositoryImpl.class);
    private String userId;
    private SocialConnectionDao socialConnectionDao;
    private ConnectionFactoryLocator connectionFactoryLocator;
    private TextEncryptor textEncryptor;

    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ConnectionRepositoryImpl(String userId, SocialConnectionDao SocialConnectionDao,
                                    ConnectionFactoryLocator connectionFactoryLocator,
                                    TextEncryptor textEncryptor) {
        this.userId = userId;
        this.socialConnectionDao = SocialConnectionDao;
        this.connectionFactoryLocator = connectionFactoryLocator;
        this.textEncryptor = textEncryptor;
    }

    /**
     * Find all connections the current user has across all providers.
     * The returned map contains an entry for each provider the user is connected to.
     * The key for each entry is the providerId, and the value is the list of {@link org.springframework.social.connect.Connection}s that exist between the user and that provider.
     * For example, if the user is connected once to Facebook and twice to Twitter, the returned map would contain two entries with the following structure:
     * <pre>
     * {
     *     "facebook" -&gt; Connection("Keith Donald") ,
     *     "twitter"  -&gt; Connection("kdonald"), Connection("springsource")
     * }
     * </pre>
     * The returned map is sorted by providerId and entry values are ordered by rank.
     * Returns an empty map if the user has no connections.
     */
    public MultiValueMap<String, Connection<?>> findAllConnections() {
        List<Connection<?>> resultList = getConnectionsFromSocialConnectionList(socialConnectionDao.findByUserId(userId));
        MultiValueMap<String, Connection<?>> connections = new LinkedMultiValueMap<String, Connection<?>>();
        Set<String> registeredProviderIds = connectionFactoryLocator.registeredProviderIds();
        for (String registeredProviderId : registeredProviderIds) {
            connections.put(registeredProviderId, Collections.<Connection<?>>emptyList());
        }
        for (Connection<?> connection : resultList) {
            String providerId = connection.getKey().getProviderId();
            if (connections.get(providerId).size() == 0) {
                connections.put(providerId, new LinkedList<Connection<?>>());
            }
            connections.add(providerId, connection);
        }
        return connections;
    }

    /**
     * @param SocialConnections
     * @return
     */
    private List<Connection<?>> getConnectionsFromSocialConnectionList(List<SocialConnection> SocialConnections) {
        List<Connection<?>> list = new LinkedList<Connection<?>>();
        if (SocialConnections != null) {
            for (SocialConnection SocialConnection : SocialConnections) {
                ConnectionData connectionData = getConnectionDataBySocialConnection(SocialConnection);
                ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
                list.add(connectionFactory.createConnection(connectionData));
            }
        }
        return list;
    }

    private Connection<?> getConnectionFromSocialConnection(SocialConnection SocialConnection) {
        ConnectionData connectionData = getConnectionDataBySocialConnection(SocialConnection);
        ConnectionFactory<?> connectionFactory = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId());
        return connectionFactory.createConnection(connectionData);
    }

    /**
     * 根据数据库中的SocialConnection记录转换为connection
     *
     * @param user
     * @return
     */
    private ConnectionData getConnectionDataBySocialConnection(SocialConnection user) {
        return new ConnectionData(user.getProviderId(), user.getProviderUserId(), user.getDisplayName(), user.getProfileUrl(), user.getImageUrl(),
                decrypt(user.getAccessToken()), decrypt(user.getSecret()), decrypt(user.getRefreshToken()), expireTime(user.getExpireTime()));
    }

    private String decrypt(String encryptedText) {
        return encryptedText != null ? textEncryptor.decrypt(encryptedText) : encryptedText;
    }

    private String encrypt(String encryptedText) {
        return encryptedText != null ? textEncryptor.encrypt(encryptedText) : encryptedText;
    }

    private Long expireTime(long expireTime) {
        return expireTime == 0 ? null : expireTime;
    }

    /**
     * Find the connections the current user has to the provider registered by the given id e.g. 'facebook'.
     * The returned list is ordered by connection rank.
     * Returns an empty list if the user has no connections to the provider.
     *
     * @param providerId the provider id e.g. "facebook"
     * @return the connections the user has to the provider, or an empty list if none
     */
    public List<Connection<?>> findConnections(String providerId) {
        List<SocialConnection> SocialConnections = socialConnectionDao.findByUserIdAndProviderId(userId, providerId);
        return getConnectionsFromSocialConnectionList(SocialConnections);
    }

    /**
     * Find the connections the current user has to the provider of the given API e.g. Facebook.class.
     * Semantically equivalent to {@link #findConnections(String)}, but uses the apiType as the provider key instead of the providerId.
     * Useful for direct use by application code to obtain parameterized Connection instances e.g. <code>List&lt;Connection&lt;Facebook&gt;&gt;</code>.
     *
     * @param <A>     the API parameterized type
     * @param apiType the API type e.g. Facebook.class or Twitter.class
     * @return the connections the user has to the provider of the API, or an empty list if none
     */
    @SuppressWarnings("unchecked")
    public <A> List<Connection<A>> findConnections(Class<A> apiType) {
        List<?> connections = findConnections(getProviderId(apiType));
        return (List<Connection<A>>) connections;
    }

    private <A> String getProviderId(Class<A> apiType) {
        return connectionFactoryLocator.getConnectionFactory(apiType).getProviderId();
    }

    /**
     * Find the connections the current user has to the given provider users.
     * The providerUsers parameter accepts a map containing an entry for each provider the caller is interested in.
     * The key for each entry is the providerId e.g. "facebook", and the value is a list of provider user ids to fetch connections to e.g. ("126500", "34521", "127243").
     * The returned map has the same structure and order, except the provider userId values have been replaced by Connection instances.
     * If no connection exists between the current user and a given provider user, a null value is returned for that position.
     *
     * @param providerUsers the provider users map
     * @return the provider user connection map
     */

    public MultiValueMap<String, Connection<?>> findConnectionsToUsers(MultiValueMap<String, String> providerUsers) {
        if (providerUsers == null || providerUsers.isEmpty()) {
            throw new IllegalArgumentException("Unable to execute find: no providerUsers provided");
        }
        List<SocialConnection> SocialConnectionList = socialConnectionDao.findByUserIdAndProviderUserIds(userId, providerUsers);
        List<Connection<?>> connectionList = getConnectionsFromSocialConnectionList(SocialConnectionList);
        //将查询到的connection组装成map，便于取用
        Map<String, Map<String, Connection<?>>> connectionMap = new HashMap<String, Map<String, Connection<?>>>();
        for (Connection<?> connection : connectionList) {
            String providerId = connection.getKey().getProviderId();
            Map<String, Connection<?>> userConnectionMap = connectionMap.get(providerId);
            if (userConnectionMap == null) {
                userConnectionMap = new HashMap<String, Connection<?>>();
                connectionMap.put(providerId, userConnectionMap);
            }
            String providerUserId = connection.getKey().getProviderUserId();
            userConnectionMap.put(providerUserId, connection);
        }

        MultiValueMap<String, Connection<?>> connectionsForUsers = new LinkedMultiValueMap<String, Connection<?>>();
        for (String providerId : providerUsers.keySet()) {
            List<String> providerUserIds = providerUsers.get(providerId);
            Map<String, Connection<?>> userConnectionMap = connectionMap.get(providerId);

            List<Connection<?>> tmpList = new LinkedList<Connection<?>>();
            for (String providerUserId : providerUserIds) {
                Connection<?> tmp = null;
                if (userConnectionMap != null) {
                    tmp = userConnectionMap.get(providerUserId);
                }
                tmpList.add(tmp);
            }
            connectionsForUsers.put(providerId, tmpList);
        }
        return connectionsForUsers;
    }

    /**
     * Get a connection for the current user by its key, which consists of the providerId + providerUserId.
     *
     * @param connectionKey the service provider connection key
     * @return the connection
     * @throws org.springframework.social.connect.NoSuchConnectionException
     *          if no such connection exists for the current user
     */
    public Connection<?> getConnection(ConnectionKey connectionKey) {
        SocialConnection SocialConnection = socialConnectionDao.get(userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        if (SocialConnection != null) {
            return getConnectionFromSocialConnection(SocialConnection);
        }
        throw new NoSuchConnectionException(connectionKey);
    }

    /**
     * Get a connection between the current user and the given provider user.
     * Semantically equivalent to {@link #getConnection(org.springframework.social.connect.ConnectionKey)}, but uses the apiType as the provider key instead of the providerId.
     * Useful for direct use by application code to obtain a parameterized Connection instance.
     *
     * @param <A>            the API parameterized type
     * @param apiType        the API type e.g. Facebook.class or Twitter.class
     * @param providerUserId the provider user e.g. "126500".
     * @return the connection
     * @throws org.springframework.social.connect.NoSuchConnectionException
     *          if no such connection exists for the current user
     */
    @SuppressWarnings("unchecked")
    public <A> Connection<A> getConnection(Class<A> apiType, String providerUserId) {
        String providerId = getProviderId(apiType);
        return (Connection<A>) getConnection(new ConnectionKey(providerId, providerUserId));
    }

    /**
     * Get the "primary" connection the current user has to the provider of the given API e.g. Facebook.class.
     * If the user has multiple connections to the provider associated with the given apiType, this method returns the one with the top rank (or priority).
     * Useful for direct use by application code to obtain a parameterized Connection instance.
     *
     * @param <A>     the API parameterized type
     * @param apiType the API type e.g. Facebook.class or Twitter.class
     * @return the primary connection
     * @throws org.springframework.social.connect.NotConnectedException
     *          if the user is not connected to the provider of the API
     */
    @SuppressWarnings("unchecked")
    public <A> Connection<A> getPrimaryConnection(Class<A> apiType) {
        String providerId = getProviderId(apiType);
        Connection<A> connection = (Connection<A>) findPrimaryConnection(providerId);
        if (connection == null) {
            throw new NotConnectedException(providerId);
        }
        return connection;
    }

    private Connection<?> findPrimaryConnection(String providerId) {
        SocialConnection SocialConnection = socialConnectionDao.findPrimaryByUserIdAndProviderId(userId, providerId);
        if (SocialConnection != null) {
            return getConnectionFromSocialConnection(SocialConnection);
        } else {
            return null;
        }
    }

    /**
     * Find the "primary" connection the current user has to the provider of the given API e.g. Facebook.class.
     * Semantically equivalent to {@link #getPrimaryConnection(Class)} but returns null if no connection is found instead of throwing an exception.
     *
     * @param <A>     the API parameterized type
     * @param apiType the API type e.g. Facebook.class or Twitter.class
     * @return the primary connection, or <code>null</code> if not found
     */
    @SuppressWarnings("unchecked")
    public <A> Connection<A> findPrimaryConnection(Class<A> apiType) {
        String providerId = getProviderId(apiType);
        return (Connection<A>) findPrimaryConnection(providerId);
    }

    /**
     * Add a new connection to this repository for the current user.
     * After the connection is added, it can be retrieved later using one of the finders defined in this interface.
     *
     * @param connection the new connection to add to this repository
     * @throws org.springframework.social.connect.DuplicateConnectionException
     *          if the user already has this connection
     */
    @Transactional
    public void addConnection(Connection<?> connection) {
        Account account = accountDao.findById(userId);//必须先有用户才能添加social_connection
        if (account == null) {
            throw new RuntimeException(String.format("没有找到[userId=%s]的account", userId));
        }
        ConnectionData data = connection.createData();
        List<SocialConnection> socialConnectionList = socialConnectionDao.findByUserIdAndProviderId(userId, data.getProviderId());
        Integer rank = 1;
        if (socialConnectionList.size() > 0) {
            rank = socialConnectionList.get(socialConnectionList.size() - 1).getRank() + 1;
        }

        SocialConnection socialConnection = null;
        socialConnection = new SocialConnection(userId, data, rank);

        encrypt(socialConnection);
        socialConnectionDao.insert(socialConnection);

        logger.info(String.format("用户[%s]将[%s]账号providerUserId=%s连接到本站", userId, connection.getKey().getProviderId(), connection.getKey().getProviderUserId()));
    }

    private void encrypt(SocialConnection SocialConnection) {
        SocialConnection.setAccessToken(encrypt(SocialConnection.getAccessToken()));
        SocialConnection.setSecret(encrypt(SocialConnection.getSecret()));
        SocialConnection.setRefreshToken(encrypt(SocialConnection.getRefreshToken()));
    }

    /**
     * Update a Connection already added to this repository.
     * Merges the field values of the given connection object with the values stored in the repository.
     *
     * @param connection the existing connection to update in this repository
     */
    public void updateConnection(Connection<?> connection) {
        ConnectionData data = connection.createData();
        SocialConnection SocialConnection = socialConnectionDao.get(userId, data.getProviderId(), data.getProviderUserId());
        SocialConnection.update(userId, data);
        encrypt(SocialConnection);
        socialConnectionDao.update(SocialConnection);
    }


    /**
     * Remove all Connections between the current user and the provider from this repository.
     * Does nothing if no provider connections exist.
     *
     * @param providerId the provider id e.g. 'facebook'
     */
    public void removeConnections(String providerId) {
        List<SocialConnection> SocialConnection = socialConnectionDao.findByUserIdAndProviderId(userId, providerId);
        socialConnectionDao.delete(SocialConnection);
    }


    /**
     * Remove a single Connection for the current user from this repository.
     * Does nothing if no such connection exists.
     *
     * @param connectionKey the connection key
     */
    public void removeConnection(ConnectionKey connectionKey) {
        SocialConnection SocialConnection = socialConnectionDao.get(userId, connectionKey.getProviderId(), connectionKey.getProviderUserId());
        socialConnectionDao.delete(SocialConnection);
    }

}
