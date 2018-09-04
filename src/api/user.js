import http  from 'axios'

const proxyURL = '/p'
const baseURL = proxyURL+'/p'
/**
 * @function 用户登录 
 * @param 见接口说明
 */
export const login = (params) => {
    return http(`${baseURL}/login.action`,{params})
        .then(res =>{
            console.log(err)
        })
        .catch(err => {
            console.log(err)
        })
}
/**
 * @function 注销当前用户
 */
export const logout = () => {
    return http(`${baseURL}/login!logout.action`,{})
}
/**
 * @function 获取当前用户信息
 */
export const getUserInfo = () => {
    return http(`${baseURL}/user!query.action`,{})
}

/**
 * @function 教师修改自己的信息
 */
export const editUserInfo = (params) => {
    return http(`${baseURL}/thr!editMy.action`,{params})
}
/**
 * @function 教师修改自己的密码
 */
export const changeUserPwd = (params) => {
    return http(`${baseURL}/thr!changePsw.action`,{params})
}