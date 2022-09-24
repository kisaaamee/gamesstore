<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">My room</h1>
    <hr>
    <br>
    <div style="text-align: center">
        <img src="https://img.cppng.com/download/2020-06/63841-profile-twitch-youtube-avatar-discord-free-download-image.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
        Email: <b>${user.email}</b>
        <br>
        <#if user.isAdmin()>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/admin">
                Admin panel
            </a><br>
            <a class="btn btn-dark mb-3" style="text-align: center; width: 100%"
               href="/my/products">
                My products
            </a>
        </#if>
        <form action="/logout" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <button type="submit" style="text-align: center; width: 100%" class="btn btn-danger mb-2">
                Log out
            </button>
        </form>
    </div>
</@t.template>