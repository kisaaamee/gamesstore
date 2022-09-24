<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">My games</h1>
    <hr>
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Add new game
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/gameBox/create" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Game title</label>
                <input type="text" required class="form-control"
                       id="productName" name="title">
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="unitPrice">
                    <div class="input-group-append">
                        <span class="input-group-text">PLN</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea required class="form-control" id="description" name="description"></textarea>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input required type="file" name="file1" id="file1">
                    <label class="custom-file-label" for="file1">Image 1</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file2" id="file2">
                    <label class="custom-file-label" for="file2">Image 2</label>
                </div>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file3" id="file3">
                    <label class="custom-file-label" for="file3">Image 3</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>

            <button type="submit" style="width: 100%" class="btn btn-dark">Add</button>
        </form>
    </div>
    <div class="card-columns">
        <#list gameBoxes as gameBox>
            <a href="/gameBox/${gameBox.id}" class="product-link">
                <div class="card text-white bg-dark my-3 product">
                    <div class="card-top">
                        <div class="btn-group" role="group" aria-label="">
                            <form action="/gameBox/delete/${gameBox.id}" method="post">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-danger m-3">
                                    Delete
                                </button>
                            </form>
                            <form action="/gameBox/edit/${gameBox.id}" method="get">
                                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                                <button type="submit" class="btn btn-warning m-3">
                                    Edit
                                </button>
                            </form>
                        </div>
                    </div>
                    <img src="/images/${gameBox.previewImageId}"
                         class="card-img-top">
                    <div class="card-body">
                        <h5 class="card-title">${gameBox.title}</h5>
                        <p class="card-text">${gameBox.unitPrice} PLN</p>
                    </div>
                </div>
            </a>
        <#else>
            <h1 style="text-align: center">Nothing here</h1>
        </#list>
    </div>
</@t.template>