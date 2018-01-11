<#import "/spring.ftl" as spring/>
<html>
<head>
    <title><@spring.message "message.index.title"/></title>
</head>
<body>
<p>
<h2><@spring.message "message.hello"/></h2>

<div>
    <h4><@spring.message "message.productList"/></h4>
    <div>
        <table>
            <tr>
                <td><b><@spring.message "message.name"/></b></td>
                <td><b><@spring.message "message.price"/></b></td>
                <td><b><@spring.message "message.quantity"/></b></td>
                <td></td>
            </tr>
    <#list productsWithQuantities as productWithQuantity>
        <tr>
            <td>${productWithQuantity.product.name}</td>
            <td>${productWithQuantity.product.price}</td>
            <td>${productWithQuantity.quantity}</td>
            <td><button><@spring.message "message.withdraw"/></button> </td>
        </tr>
    </#list>
        </table>
    </div>
    <p>
    <button><@spring.message "message.add"/></button>
</div>
</body>
</html>