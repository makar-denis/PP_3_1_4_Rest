const urlUserInfo = 'http://localhost:8080/api/users'

let userInfo = document.querySelector('#userInfo')
fetch(urlUserInfo)
    .then(res => res.json())
    .then(user => {
        userInfo.innerHTML = `
                                <b> ${user.username}</b>
                                <b>with roles:</b>
                                <b> ${user.roles.map(r=> ' '+r.name)}</b>
                                 `;
    })

