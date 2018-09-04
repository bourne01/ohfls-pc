import {getTeachingSites} from '../../api/teaching-site'
import * as type from './mutations-type'



const state = {
    teachingTable:[] //获取教学场地信息
}

const getters= {

}

const mutations = {
    [type.GET_TEACHINGSITE](state,params){
        state.teachingTable = params;
    }
}

const actions = {
    getTeachingSite({commit},params){
        return new Promise((resolve,reject) => {
            getTeachingSites(params.url,params.params)
                .then(res => {
                    commit('GET_TEACHINGSITE',res.data.dataList);
                    resolve(res.data);
                })
                .catch(err => {
                    console.log(err)
                    reject(err)
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

