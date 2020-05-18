import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
    state:{
        select: '',
        flightList:[],
        backFlightList:[],
        admin:[]
    },
    mutations:{
        newSelect(state, n) {
            state.select = n
        },
        newFlightList(state, n) {
            state.flightList = n
        },
        newBackFlightList(state, n) {
            state.backFlightList = n
        },
        newAdmin(state, n) {
            state.admin = n
        }
    },
    actions: {
        actionsNewSelect(context,n) {
            return context.commit('newSelect', n)
        },
        actionsnewFlightList(context,n) {
            return context.commit('newFlightList', n)
        },
        actionsnewBackFlightList(context,n) {
            return context.commit('newBackFlightList', n)
        },
        actionsnewAdmin(context,n) {
            return context.commit('newAdmin', n)
        },
    }
  })