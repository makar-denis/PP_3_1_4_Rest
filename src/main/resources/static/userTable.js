const urlUserTable = 'http://localhost:8080/api/admin'
const urlRole = 'http://localhost:8080/api/rol'

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

fetch(urlUserTable)
    .then(res => res.json())
    .then(users => {
        console.log(users); // смотрю в консоли
        if (users.length>0){
            var temp=""
            users.forEach((us)=>{
                temp +='<tr id="'+us.id+'">'
                temp +="<td>"+ us.id
                temp +="<td>"+ us.username
                temp +="<td>"+ us.lastName
                temp +="<td>"+ us.age
                temp +="<td>"+ us.email
                temp +="<td>"+ us.roles.map(r=>' '+r.name)
                temp += "<td>"+'<button type="button" onclick="functionEdit(' + us.id + ')" '
                    + 'class="btn btn-primary">Edit</button>';
                temp += "<td>"+'<button type="button" onclick="functionDelet(' + us.id + ')" '
                    + 'class="btn btn-danger">Delete</button>';
            })
            document.querySelector('#userTable').innerHTML = temp
        }
    })

function functionEdit(id){
    console.log('Сработало нажатие кнопки редактирования')
    fetch('http://localhost:8080/api/admin/' +id)
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
            // $("#rolesEdit").empty()
            // listRol = '<option> id="idRole"'+'ROLE_USER'+'</option>'+'<option>'+'ROLE_ADMIN'+'</option>'
            // $("#rolesEdit").append(listRol)
        })
}

function fEdit() {
    let id = window.formModalEdit.idEdit.value
    let rol = window.formModalEdit.rolesEdit.value
    console.log('Включилась fEdit()')
    console.log(id)
    fetch('http://localhost:8080/api/update?roles='+rol, {
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
    })
        // .then(response => (
        //     console.log('отправлен запрос tgggg')))

    //         res => res.json())
        .then(response => {
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

    fetch('http://localhost:8080/api/admin/' +id)
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
    fetch('http://localhost:8080/api/delete/'+id, {
        method: 'DELETE',
        headers: { 'Content-Type': 'application/json; charset=utf-8'}
    }).then(response => (
        console.log('отправлен запрос delete')
        // $('#' + id).remove()
        )
)
}

function newUser() {
    let rol = window.formNewUser.rolesNewUser.value
    console.log('нажата кнопка создания пользователя')
    fetch('http://localhost:8080/api/create?roles='+rol, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json; charset=utf-8'},
        body: JSON.stringify({
            userName: window.formNewUser.userNameNewUser.value,
            lastName: window.formNewUser.lastNameNewUser.value,
            age: window.formNewUser.ageNewUser.value,
            email: window.formNewUser.emailNewUser.value,
            password: window.formNewUser.passwordNewUser.value
        })
    }).then(res => res.json())
        .then(us => console.log(us))
            .then (data=>{
                console.log('отправлен запрос create')

            $(`#userTable`).after(`<tr id=` + user.id + `>` +
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


//Заменить на новую таблицу
//             var temp=""
//             temp +="<tr id='"+us.id+"'>"
//             temp +="<td>"+ us.id
//             temp +="<td>"+ us.username
//             temp +="<td>"+ us.lastName
//             temp +="<td>"+ us.age
//             temp +="<td>"+ us.email
//             temp +="<td>"+ us.roles.map(r=>' '+r.name)
//             temp += "<td>"+'<button type="button" onclick="functionEdit(' + us.id + ')" '
//                 + 'class="btn btn-primary">Edit</button>';
//             temp += "<td>"+'<button type="button" onclick="functionDelet(' + us.id + ')" '
//                 + 'class="btn btn-danger">Delete</button>';
//
//             $("#"+id).replaceWith(temp);
//             console.log(temp)

}