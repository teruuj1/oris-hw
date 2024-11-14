<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>
<#macro content>
    Users:
    <br>
    <#if users?has_content>
        <#list users as u>
            ${u.name}
            <br>
        </#list>
    </#if>
</#macro>
</html>