<script>
import axios from 'axios';
import PizzaCard from '../pizzas/PizzaCard.vue';

const baseApiUrl = 'http://localhost:8080/api/v1/';

export default {
    name: "Pizzeria Home",
    data() {
        return {
            pizze: [],
            searchTerm: '',
            showId: -1
        };
    },
    methods: {
        fetchPizze() {
            axios.get(baseApiUrl + "pizzas",
                {
                    params: {
                        name: this.searchTerm.trim(),
                    }
                })
                .then(res => this.pizze = res.data)
                .catch(e => console.log(e));
        },

        deletePizza(id) {

            axios.delete(baseApiUrl + 'pizzas/delete/' + id)
                .then(res => {
                    this.fetchPizze();
                    this.showId = -1;
                })
                .catch(e => console.log(e))
        },
        showSelectedPizza(id) {
            this.showId = id
        }
    },
    mounted() {
        this.fetchPizze();
    },
    components: { PizzaCard }
}
</script>

<template>
    <main class="container text-center">
        <h1 class="my-5">Pizzeria</h1>
        <div v-if="showId === -1" class="d-flex justify-content-center">
            <div class="col-6">
                <div class="input-group mb-3">
                    <input type="text" class="form-control" placeholder="Cerca pizza" aria-label="Recipient's username"
                        aria-describedby="button-addon2" v-model="searchTerm" @keyup.enter="fetchPizze()">
                    <button class="btn btn-primary" type="button" id="button-addon2" @click="fetchPizze()"><i
                            class="fa-solid fa-magnifying-glass"></i></button>
                </div>
            </div>
        </div>
        <RouterLink v-if="showId === -1" to="/create" class="btn btn-success mb-3">Crea pizza</RouterLink>
        <div class="row row-cols-3 justify-content-center">
            <PizzaCard v-show="showId === -1 || showId === pizza.id" v-for="pizza in pizze" :pizza="pizza"
                @delete="deletePizza" @show="showSelectedPizza" @no-show="showId = -1" />
        </div>
    </main>
</template>

<style scoped lang="scss"></style>