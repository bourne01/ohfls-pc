import Vuex from 'vuex'
import Teacher from './modules/teacher'
import Site from './modules/site'
import User from './modules/user'
import Election from './modules/election'
import Student from './modules/student'

const isDev = process.env.NODE_ENV === 'development'
export default () => {
    return new Vuex.Store({
        strict:isDev,//不可在生产环境使用
        state:{
            depSelectors:[],//学部下拉列表
            majSelectors:[],//专业下拉列表
            attrSelectors:[],//专业属性下拉列表
        },
        getters:{},
        mutations:{
            /**@function 设置学部下拉列表 */
            setDepSelectors(state,val){
                state.depSelectors = val
            },
            /**@function 设置专业下拉列表 */
            setMajSelectors(state,val){
                state.majSelectors = val
            },
            /**@function 设置专业属性下拉列表 */
            setAttrSelectors(state,val){
                state.attrSelectors = val
            }
        },
        actions:{
            //下拉列表
            getSelector({commit},params){
                return new Promise((resolve,reject) => {
                    getSelector(params)
                        .then(res => {
                            console.log(res);
                            if(params.f === 'uxDep'){
                                console.log(res.data.dataList)
                                commit('setDepSelectors',res.data.dataList);
                            }else if(params.f === 'uxMaj'){
                                commit('setMajSelectors',res.data.dataList);
                            }else if(params.f === 'uxCode' && params.codeType === '302'){
                                commit('setAttrSelectors',res.data.dataList);
                            }
                            resolve(res.data);
                        })
                        .catch(err => {
                            console.log(err);
                            reject(err)
                        })
                })
            },
        }, 
        modules:{
            /* management:{
                namespaced:true,                
                state:manageState,
                actions,
                mutations,
                getters,
            },  */
            // settings:Settings,
            election:Election,
            user:User,
            teacher:Teacher,
            site:Site,
            student:Student
        }
    })
}