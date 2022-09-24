<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Bucket of ${user.name}</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Title</th>
            <th scope="col">Amount</th>
            <th scope="col">Price</th>
            <th scope="col">Sum</th>
        </tr>
        <#list buckets as detail>
            <tr>
                <th>${detail.title}</th>
                <th>${detail.amount}</th>
                <th>${detail.unitPrice}</th>
                <th>${detail.sum}</th>
                <th>
                    <form action="/shoppingBucket/delete/${detail.gameBoxId}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Remove from list">
                    </form>
                </th>
            </tr>
        <#else>
            <h3>Empty</h3>
        </#list>
    </table>
    <br>
    <br>
    <button type="button" class="btn btn-success">Payment details</button>
</@t.template>