<script>
export default {
    name: "PizzaCard",
    props: {
        pizza: Object
    },
    emits: ['delete']
}
</script>

<template>
    <div class="col mb-3">
        <div class="card h-100 p-3">
            <h3 class="mb-3">{{ pizza.name }}</h3>
            <img class="mb-3" :src="pizza.imageUrl" :alt="pizza.name">
            <div class="buttons col-6 d-flex justify-content-center m-auto">
                <button type="button" class="btn btn-danger" @click="$emit('delete', pizza.id)"><i
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
</style>