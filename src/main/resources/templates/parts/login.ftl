<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> User Name: </label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="User name"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label"> Password: </label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <button class="btn btn-primary" type="submit">
            <#if isRegisterForm>
                Sign Up
            <#else>
                Sign In
            </#if>
        </button>
        <div class="mt-3">
            <#if !isRegisterForm>
                <a href="/registration">Sign up</a>
            </#if>
        </div>
    </form>
</#macro>

<#macro logout>
    <div>
        <form method="post" action="/logout">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button class="btn btn-secondary" type="submit">Sign out</button>
        </form>
    </div>
</#macro>