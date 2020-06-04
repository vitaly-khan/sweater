<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>

<div class="mb-3 h3">New user</div>
${message?if_exists}
<@l.login "/registration" true />
</@c.page>