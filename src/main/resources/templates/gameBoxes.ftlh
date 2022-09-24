<#import "blocks/template.ftlh" as t>
<@t.template user><html>

    <h1 style="text-align: center">TODAY'S BEST</h1><hr>
    <form method="get" action="/">
        <div class="input-group rounded">
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search" name="searchWorld" <#if searchWorld??>value="${searchWorld}"</#if>>
                <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </form>
    <hr>
    <div class="card-columns">
        <#list gameBoxes as gameBox>
            <a href="/gameBox/${gameBox.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <div class="btn-group" role="group" aria-label="">
<#--                            <a href="/user/${gameBox.user.id}">-->
                            <form action="/shoppingBucket/${gameBox.id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-warning m-3">
                                    Buy now
                                </button>
                            </form>
                        </div>
                    </div>
                    <img src="/images/${gameBox.previewImageId}"
                         class="card-img-top"/>
                    <div class="card-body">
                        <h5 class="card-title">${gameBox.title}</h5>
                        <p class="card-text">${gameBox.unitPrice} PLN</p>
                    </div>
                </div>
            </a>
            <#else>
            <#if user.isAdmin()>
                <form action="/gameBox/createSample" method="post">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-warning m-3">
                        Try demo games list
                    </button>
                </form>
            </#if>
            <h1 style="text-align: center">Nothing here</h1>
        </#list>
    </div>
</@t.template>