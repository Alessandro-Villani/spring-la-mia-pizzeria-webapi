<script>
export default {
    name: "PizzaCard",
    data() {
        return {
            isShown: false,
        }
    },
    props: {
        pizza: Object
    },
    methods: {
        showPizza() {
            this.$emit('show', this.pizza.id);
            this.isShown = true;
        },
        hidePizza() {
            this.$emit('no-show');
            this.isShown = false;
        },
        deletePizza() {
            this.isShown = false;
            this.$emit('delete', this.pizza.id);
        }
    },
    emits: ['delete', 'show', 'no-show']
}
</script>

<template>
    <div class="col mb-3 position-relative">
        <div v-show="isShown" class="close-button btn btn-danger" @click="hidePizza()"><i class="fa-solid fa-xmark"></i>
        </div>
        <div class="card h-100 p-3">
            <div class="card-top" @click="showPizza()">
                <h3 class="mb-3">{{ pizza.name }}</h3>
                <img class="mb-3" :src="pizza.imageUrl" :alt="pizza.name">
                <p class="mb-3" v-show="isShown">{{ pizza.description }}</p>
                <p class="mb-3" v-show="isShown">{{ pizza.price }}€</p>
            </div>
            <div class="buttons col-6 d-flex justify-content-center m-auto">
                <button type="button" class="btn btn-danger" @click="deletePizza()"><i
                        class="fa-solid fa-trash-can"></i></button>
            </div>
        </div>
    </div>
</template>

<style scoped lang="scss">
.card {
    cursor: pointer;

    img {
        max-height: 200px;
        max-width: 100%;
    }

}

.close-button {
    position: absolute;
    right: 50%;
    transform: translate(50%, -50%);
    font-size: 8px;
    z-index: 2;
}
</style>