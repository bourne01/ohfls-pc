import Vue from 'vue'
import Vuex from 'vuex'
import 'element-ui/lib/theme-chalk/index.css'
import createStore from './store/index'
import ElementUI from 'element-ui';
Vue.use(Vuex)
Vue.use(ElementUI);
/* import 'vue-awesome/icons'

import Icon from 'vue-awesome/components/Icon.vue' */
import axios from 'axios'
//import { quillEditor } from 'vue-quill-editor'
import router from './router/index.js'
import App from './App.vue'

//Vue.use(quillEditor)
//Vue.component('quill-editor',quillEditor)
//axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
const store = createStore;
Vue.prototype.$http = axios;
/* Vue.component('icon', Icon) */
new Vue({
	el: '#app',
	store,
	router,
	data:{
		bus: new Vue()
	},
	render: h => h(App)
})
