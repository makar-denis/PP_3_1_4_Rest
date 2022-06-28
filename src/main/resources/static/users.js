const url = 'http://localhost:8080/api/user'

let loggedInUser = document.querySelector('#tableOneUser')
fetch(url)
    .then(res => res.json())
    .then(user => {
        loggedInUser.innerHTML = `
                                <td>${user.id}</td>
                                <td>${user.username}</td>
                                <td>${user.lastName}</td>
                                <td>${user.age}</td>
                                <td>${user.email}</td>
                                <td> ${user.roles.map(r=> ' '+r.name)}</td>
                                 `;
    })

