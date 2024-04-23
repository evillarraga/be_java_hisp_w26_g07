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
