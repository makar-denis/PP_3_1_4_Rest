const urlUserTable = 'http://localhost:8080/admin/user'
const urlRole = 'http://localhost:8080/admin/rol'

function listRole() {
    console.log('Сработало listRole')
    fetch(urlRole)
        .then(res => res.json())
        .then(listRol => {
            list = listRol.map(r=>' <option>'+r.name+ '</option>')
            console.log(list)
            $("#rolesEdit").empty()
            $("#rolesEdit").append(list)
        })
}
function listRoleNewUser() {
    console.log('Сработало listRoleNewUser')
    fetch(urlRole)
        .then(res => res.json())
        .then(listRol => {
            list = listRol.map(r=>' <option>'+r.name+ '</option>')
            console.log(list)
            $("#rolesNewUser").empty()
            $("#rolesNewUser").append(list)
        })
}

function getAllUsers() {
    fetch(urlUserTable)
        .then(res => res.json())
        .then(users => {
            console.log(users); // смотрю в консоли
            if (users.length > 0) {
                var temp = ""
                users.forEach((us) => {
                    temp += '<tr id="' + us.id + '">'
                    temp += "<td>" + us.id
                    temp += "<td>" + us.username
                    temp += "<td>" + us.lastName
                    temp += "<td>" + us.age
                    temp += "<td>" + us.email
                    temp += "<td>" + us.roles.map(r => ' ' + r.name)
                    temp += "<td>" + '<button type="button" onclick="functionEdit(' + us.id + ')" '
                        + 'class="btn btn-primary">Edit</button>';
                    temp += "<td>" + '<button type="button" onclick="functionDelet(' + us.id + ')" '
                        + 'class="btn btn-danger">Delete</button>';
                })
                document.querySelector('#userTable').innerHTML = temp
            }
        })
}

getAllUsers()


function functionEdit(id){
    console.log('Сработало нажатие кнопки редактирования')
    fetch('http://localhost:8080/admin/user/' +id)
        .then(res => res.json())
        .then(user => {
            console.log('Получил User по id')
            $("#modalEdit").modal()
            document.getElementById("idEdit").value = user.id
            document.getElementById("userNameEdit").value = user.username
            document.getElementById("lastNameEdit").value = user.lastName
            document.getElementById("ageEdit").value = user.age
            document.getElementById("emailEdit").value = user.email
            document.getElementById("passwordEdit").value = user.password
            listRole()
        })
}


function fEdit() {
    let id = window.formModalEdit.idEdit.value
    let rol = window.formModalEdit.rolesEdit.value
    console.log('Включилась fEdit()')
    console.log(id)
    fetch('http://localhost:8080/admin/user/update?roles='+rol, {
        method: 'PUT',
        body: JSON.stringify({
            id: id,
            userName: window.formModalEdit.userNameEdit.value,
            lastName: window.formModalEdit.lastNameEdit.value,
            age: window.formModalEdit.ageEdit.value,
            email: window.formModalEdit.emailEdit.value,
            password: window.formModalEdit.passwordEdit.value
        }),
        headers: { 'Content-Type': 'application/json; charset=utf-8'}
    }).then(response => {
            console.log('Не срабатывает?????')

            temp=""
            temp +="<tr id='"+id+"'>"
            temp +="<td>"+ id
            temp +="<td>"+ window.formModalEdit.userNameEdit.value
            temp +="<td>"+ window.formModalEdit.lastNameEdit.value
            temp +="<td>"+ window.formModalEdit.ageEdit.value
            temp +="<td>"+ window.formModalEdit.emailEdit.value
            temp +="<td>"+ rol
            temp += "<td>"+'<button type="button" onclick="functionEdit(' + id + ')" '
                + 'class="btn btn-primary">Edit</button>';
            temp += "<td>"+'<button type="button" onclick="functionDelet(' + id + ')" '
                + 'class="btn btn-danger">Delete</button>';

            $("#"+id).replaceWith(temp);
            console.log(temp)

    })
}

function functionDelet(id){
    console.log('Сработало нажатие кнопки удаления')

    fetch('http://localhost:8080/admin/user/' +id)
        .then(res => res.json())
        .then(user => {
            console.log('Получил User по id')
            $("#modalDelet").modal()
            document.getElementById("idDelete").value = user.id
            document.getElementById("userNameDelete").value = user.username
            document.getElementById("lastNameDelete").value = user.lastName
            document.getElementById("ageDelete").value = user.age
            document.getElementById("emailDelete").value = user.email
            $("#rolesDelete").empty()
            listRol = user.roles.map(r=>'<option>'+r.name+'</option>')
            $("#rolesDelete").append(listRol)
})
}

function fDelet() {
    let id = window.qwert.idDelete.value
    console.log(' получено  id=' + id)
    qqq.insertAdjacentHTML('beforeend', '<h1>Admin panel</h1>v')
    fetch('http://localhost:8080/admin/user/delete/'+id, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json; charset=utf-8'}
    }).then(response => {
        console.log('отправлен запрос delete')
        })

}

function newUser() {
    let rol = window.formNewUser.rolesNewUser.value
    console.log('нажата кнопка создания пользователя')
    fetch('http://localhost:8080/admin/user/create?roles='+rol, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json; charset=utf-8'},
        body: JSON.stringify({
            userName: window.formNewUser.userNameNewUser.value,
            lastName: window.formNewUser.lastNameNewUser.value,
            age: window.formNewUser.ageNewUser.value,
            email: window.formNewUser.emailNewUser.value,
            password: window.formNewUser.passwordNewUser.value
        })
    }).then(response => response.json())
        .then(response => console.log(response))
            .then (data=>{
                console.log('отправлен запрос create')

            $(`#userTable`).append(`<tr id=` + user.id + `>` +
                `<td>` + user.id + `</td>` +
                `<td>` + window.formNewUser.userNameNewUser.value + `</td>` +
                `<td>` + window.formNewUser.lastNameNewUser.value + `</td>` +
                `<td>` + window.formNewUser.ageNewUser.value + `</td>` +
                `<td>` + window.formNewUser.emailNewUser.value + `</td>` +
                `<td>` + + us.roles.map(r=>' '+r.name) + `</td>` +
                "<td>"+'<button type="button" onclick="functionEdit(' + us.id + ')" '
                + 'class="btn btn-primary">Edit</button>'+
                "<td>"+'<button type="button" onclick="functionDelet(' + us.id + ')" '
                + 'class="btn btn-danger">Delete</button>'+
                `</tr>`);

            })
}
