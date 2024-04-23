# Social MELI
## Diagrama
## Endpoints
### US 0001
Poder realizar la acción de “Follow” (seguir) a un determinado vendedor
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center"> /users/:userId/follow/:userIdToFollow </td>
      <td>
        <ul>
          <li>200 (Todo OK)</li>
          <li>400 (Bad Request)</li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

### US 0002
Obtener el resultado de la cantidad de usuarios que siguen a un determinado vendedor
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center"> /users/:userId/followers/count </td>
<td>
  
```json
{
   "user_id":234,
   "user_name":"vendedor1",
   "followers_count":35
}
```
</td>
    </tr>
  </tbody>
</table>

### US 0003
Obtener un listado de todos los usuarios que siguen a un determinado vendedor (¿Quién me sigue?)
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center"> /users/:userId/followers/list </td>
<td>
  
```json
{
   "user_id":234,
   "user_name":"vendedor1",
   "followers":[
      {
         "user_id":4698,
         "user_name":"usuario1"
      },
      {
         "user_id":1536,
         "user_name":"usuario2"
      },
      {
         "user_id":2236,
         "user_name":"usuario3"
      }
   ]
}
```
</td>
    </tr>
  </tbody>
</table>

### US 0004
Obtener  un listado de todos los vendedores a los cuales sigue un determinado usuario (¿A quién sigo?)
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center"> /users/:userId/followed/list </td>
<td>
  
```json
{
   "user_id":4698,
   "user_name":"usuario1",
   "followed":[
      {
         "user_id":234,
         "user_name":"vendedor1"
      },
      {
         "user_id":6932,
         "user_name":"vendedor2"
      },
      {
         "user_id":6631,
         "user_name":"vendedor3"
      }
   ]
}
```
</td>
    </tr>
  </tbody>
</table>

### US 0005
Dar de alta una nueva publicación
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Payload</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Post</td>
      <td align="center"> /products/post </td>
<td>
  
```json
{
   "user_id":123,
   "date":"29-04-2021",
   "product":{
      "product_id":1,
      "product_name":"Silla Gamer",
      "type":"Gamer",
      "brand":"Racer",
      "color":"Red & Black",
      "notes":"Special Edition"
   },
   "category":100,
   "price":1500.50
}
```
</td>
    <td align="center">
        <ul>
          <li>200 (Todo OK)</li>
          <li>400 (Bad Request)</li>
        </ul>  
    </td>
    </tr>
  </tbody>
</table>

### US 0006
Obtener un listado de las publicaciones realizadas por los vendedores que un usuario sigue en las últimas dos semanas (para esto tener en cuenta ordenamiento por fecha, publicaciones más recientes primero).
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
      <th>Response</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center"> /products/followed/:userId/list </td>
<td>
  
```json
{
   "user_id":4698,
   "posts":[
      {
         "user_id":123,
         "post_id":32,
         "date":"01-05-2021",
         "product":{
            "product_id":62,
            "product_name":"Headset RGB Inalámbrico",
            "type":"Gamer",
            "brand":"Razer",
            "color":"Green with RGB",
            "notes":"Sin Batería"
         },
         "category":120,
         "price":2800.69
      },
      {
         "user_id":234,
         "post_id":18,
         "date":"29-04-2021",
         "product":{
            "product_id":1,
            "productName":"Silla Gamer",
            "type":"Gamer",
            "brand":"Racer",
            "color":"Red & Black",
            "notes":"Special Edition"
         },
         "category":100,
         "price":15000.50
      }
   ]
}
```
</td>
    </tr>
  </tbody>
</table>

### US 0007
Poder realizar la acción de “Unfollow” (dejar de seguir) a un determinado vendedor.
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
    </tr>
    <tr>
      <td align="center">Post</td>
      <td align="center"> /users/:userId/unfollow/:userIdToUnfollow </td>
    </tr>
  </tbody>
</table>

### US 0008
Ordenamiento alfabético ascendente y descendente
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center">
        <ul>
          <li> /users/:userId/followers/list?order=name_asc </li>
          <li> /users/:userId/followers/list?order=name_desc </li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>

### US 0009
Ordenamiento por fecha ascendente y descendente
<table>
  <tbody>
    <tr>
      <th>Method</th>
      <th>Url</th>
    </tr>
    <tr>
      <td align="center">Get</td>
      <td align="center">
        <ul>
          <li> /products/followed/:userId/list?order=date_asc </li>
          <li> /products/followed/:userId/list?order=date_desc </li>
        </ul>
      </td>
    </tr>
  </tbody>
</table>
