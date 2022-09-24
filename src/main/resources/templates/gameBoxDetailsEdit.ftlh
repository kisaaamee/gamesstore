<#import "blocks/template.ftlh" as t>
<@t.template user>
    <hr xmlns="http://www.w3.org/1999/html">
    <a class="btn btn-dark mt-3 mb-3" style="text-align: center; width: 100%"
       data-toggle="collapse" href="#collapseExample" role="button"
       aria-expanded="true" aria-controls="collapseExample">
        Edit game
    </a>
    <div class="collapse mb-3" id="collapseExample" style="">
        <form action="/gameBox/saveEdited" method="post" enctype="multipart/form-data">
            <div class="form-group">
                <label for="productName">Game title</label>
                <input type="text" required class="form-control"
                       id="productName" name="title" value="${gameBox.title}">
            </div>
            <div class="form-group">
                <label for="price">Price</label>
                <div class="input-group mb-3">
                    <input type="number" required class="form-control" id="price" name="unitPrice" value="${gameBox.unitPrice}">
                    <div class="input-group-append">
                        <span class="input-group-text">PLN</span>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label for="description">Description</label>
                <textarea required class="form-control" id="description" name="description">${gameBox.description}"</textarea>
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
            <input type="hidden" value="${gameBox.id}" name="gameBoxId">
            <button type="submit" style="width: 100%" class="btn btn-dark">Save</button>
        </form>
    </div>
</@t.template>
