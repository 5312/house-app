import Vue from 'vue'
import App from './App'

Vue.config.productionTip = false

import create from "utils/create.js"
import tool from "utils/tool.js"
Vue.prototype.$create=create
Vue.prototype.$tool=tool
import AceAlert from "@/components/ace/ace-alert.vue"
Vue.component('ace-alert',AceAlert)


import uView from "uview-ui";
Vue.use(uView);

App.mpType = 'app'

const app = new Vue({
    ...App
})
app.$mount()
