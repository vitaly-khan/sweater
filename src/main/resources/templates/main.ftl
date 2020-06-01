<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<@c.page>
    <@l.logout />
    <div>
        <form method="post" action="/main">
            <input type="text" name="text" placeholder="Input a message"/>
            <input type="text" name="tag" placeholder="Tag"/>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit">Send</button>
            <button type="reset">Reset</button>
            <button type="reset">Reset</button>
        </form>
    </div>
    <div>
        <form method="get" action="/main">
            <input type="text" name="filter" value="${filter}"/>
            <button type="submit">Find</button>
            <button type="reset">Reset</button>
        </form>
    </div>
    <div>List of messages</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
            <i>${message.tag}</i>
            by
            <strong>${(message.author.username)!"&lt;none&gt;"}</strong>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>