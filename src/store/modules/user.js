import {login,logout,getUserInfo} from '../../api/user'
import * as type from './mutations-type'

const state = {
    user:null,//登陆成功后获取的用户对象
    username:'',
}

const getters = {

}

const mutations = {
    /**设置用户对象 */
    [type.GET_CURRENT_USER](state,params){
        state.user = params;
    },
    /**设置用户对象 */
    [type.SET_USERNAME](state,params){
        state.username = params;
    },
    
}

const actions = {
    /**
     * @function 用户登录
     * @returns Promise 对象 
     */
    login (ctx,params) {
        return login(params)
    },
    /**
     * @function 注销当前用户
     */
    logout(){
        return logout();
    },
    /**
     * @function 获取当前用户信息 
     */
    getUserInfo(){
        return new Promise((resolve,reject) => {
            getUserInfo()
                .then(res => {
                    commit('GET_CURRENT_USER',res.data);
                    resolve(res.data)
                })
                .catch(err => {
                    reject(err);
                })
        })
    }

}

export default {
    namespaced:true,
    state,
    getters,
    mutations,
    actions
}