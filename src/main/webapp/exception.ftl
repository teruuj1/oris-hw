<html lang="en">
<#include "base.ftl">

<#macro title>
    exception details
</#macro>

<#macro content>
    <h1>exception details</h1>
    <strong>request url: ${url}</strong>
    <strong>status code: ${statusCode}</strong>
    <#if message ??><strong>${message}</strong></#if>
</#macro>

</html>