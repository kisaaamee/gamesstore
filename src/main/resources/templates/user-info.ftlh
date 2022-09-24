<#import "blocks/template.ftlh" as t>
<@t.template userByPrincipal>
    <h1 style="text-align: center">User details</h1>
    <hr>
    <div style="text-align: center">
        <img src="https://img.cppng.com/download/2020-06/63841-profile-twitch-youtube-avatar-discord-free-download-image.png"
             style="border:2px solid black; border-radius: 20px" height="120">
        <br>
        <br>
        <h2>${user.name}</h2>
        <br>
    </div>
    <br>
    <hr>
    <h1 style="text-align: center">${user.name}'s games</h1>
    <br>
    <div class="card-columns">
        <#list gameBoxes as gameBox>
            <a href="/gameBox/${gameBox.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">

                    <img src="/images/${gameBox.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${gameBox.title}</h5>
                        <p class="card-text">${gameBox.unitPrice} PLN</p>
                    </div>
                    <div class="card-footer">
                        ${gameBox.user.name}
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Nothing found</h1>
        </#list>
    </div>
</@t.template>
