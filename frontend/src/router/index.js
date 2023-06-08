import {createRouter, createWebHistory} from 'vue-router';
import HomePage from '../components/pages/HomePage.vue';
import PizzaCreate from '../components/pages/PizzaCreate.vue'

const router = createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: '/',
            name: 'Home',
            component: HomePage
        },
        {
            path: '/create',
            name: 'Create',
            component: PizzaCreate
        }
    ]
});

export {router};