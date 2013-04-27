以下是可连接的provider
<ul>
<#list providerIds as providerId>
    <li><a href="${request.getContextPath()}/connect/${providerId}">${providerId}</a></li>
</#list>
</ul>