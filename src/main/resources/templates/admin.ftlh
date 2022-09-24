<#import "blocks/template.ftlh" as t>
<@t.template user>
    <h1 style="text-align: center">Admin panel</h1><hr>
    <table class="table table-hover table-dark">
        <tr>
            <th scope="col">Email</th>
            <th scope="col">Active</th>
            <th scope="col">User type</th>
            <th scope="col">Block status</th>
            <th scope="col">Actions</th>
            <th scope="col">Details</th>
        </tr>
        <#list users as user1>
            <tr>
                <th>${user1.email}</th>
                <th><#if user1.active>true<#else>false</#if></th>
                <th><#list user1.typeOfUser as role>${role}</#list></th>
                <th>
                    <form action="/admin/user/ban/${user1.id}" method="post">
                        <input type="hidden" name="_csrf" value="${_csrf.token}">
                        <input type="submit" class="btn btn-light" value="Block/Unblock">
                    </form>
                </th>
                <th><a href="/admin/user/edit/${user1.id}">Edit</a></th>
                <th><a href="/user/${user1.id}">More</a></th>
            </tr>
        <#else>
            <h3>No users yet</h3>
        </#list>
    </table>
</@t.template>