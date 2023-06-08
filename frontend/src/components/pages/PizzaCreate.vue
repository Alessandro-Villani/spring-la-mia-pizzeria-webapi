<script>
import axios from 'axios';

const baseApiUrl = 'http://localhost:8080/api/v1/';

export default {
    name: 'Pizza Create',
    data() {
        return {
            pizza: {
                name: '',
                description: '',
                imageUrl: '',
                price: ''
            }
        }
    },
    methods: {
        addPizza() {
            axios.post(baseApiUrl + 'pizzas/store', this.pizza)
                .then(res => {
                    this.$router.push("/");
                })
                .catch(e => console.log(e))
        }
    }
}
</script>

<template>
    <main class="container text-center">
        <h1 class="my-5">Create</h1>
        <form class="d-flex flex-column align-items-center" @submit.prevent="addPizza">
            <label for="name">Nome</label>
            <input class="mb-3" id="name" type="text" name="name" v-model="pizza.name">
            <label for="description">Descrizione</label>
            <input class="mb-3" id="description" type="text" name="description" v-model="pizza.description">
            <label for="imageUrl">Url immagine</label>
            <input class="mb-3" id="imageUrl" type="text" name="imageUrl" v-model="pizza.imageUrl">
            <label for="price">Prezzo</label>
            <input class="mb-5" id="price" type="number" step="0.01" name="price" v-model="pizza.price">
            <div class="buttons d-flex justify-content-between col-2">
                <button class="btn btn-success">Crea</button>
                <RouterLink class="btn btn-secondary" to="/">Indietro</RouterLink>
            </div>
        </form>
    </main>
</template>

<style scoped lang="scss"></style>