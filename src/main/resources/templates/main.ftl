<#import "parts/common.ftl" as c>
<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" value="${filter}" placeholder="Search by tag"/>
                <button class="btn btn-primary ml-2" type="submit">Search</button>
            </form>
        </div>
    </div>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseForm" role="button" aria-expanded="false"
       aria-controls="collapseForm">
        Add new message
    </a>
    <div class="collapse" id="collapseForm">
        <div class="form-group mt-3">
            <form method="post" action="/main" enctype="multipart/form-data">
                <div class="form-group">
                    <input class="form-control" type="text" name="text" placeholder="Input a message"/>
                </div>
                <div class="form-group">
                    <input class="form-control" type="text" name="tag" placeholder="Tag"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile">
                        <label class="custom-file-label" for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button class="btn btn-primary ml-2" type="submit">Send</button>
                </div>
            </form>
        </div>
    </div>

    <#list messages as message>
        <div class="card my-3" style="width: 38rem;">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span>
                <i>${message.tag}</i>
            </div>
            <div class="card-footer text-muted text-right" >
                <small>
                    by <strong>${(message.author.username)!"&lt;none&gt;"}</strong>
                </small>
            </div>
        </div>
    <#else>
        No messages
    </#list>
</@c.page>