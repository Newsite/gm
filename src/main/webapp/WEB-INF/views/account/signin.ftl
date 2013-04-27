<div class="row">
    使用以下方式登陆:
</div>
<div class="row">
<#list providerIdSet as providerId>
    <div class="large-12 columns">
        <form action="${request.getContextPath()}/signin/${providerId}" method="post">
            <input name="submit" type="image" value=""
                   src="${request.getContextPath()}/static/images/saas/${providerId}.ico"/>
        </form>
    </div>
</#list>
</div>