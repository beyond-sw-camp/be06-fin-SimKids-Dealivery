<template>
    <div class="css-heioij eug5r8l1">
        <div class="css-oc8mjz ed9qr673">
            <div class="css-eq7f8j ed9qr672">
                <h2 class="css-1lmd4kz ed9qr671">{{ title }}</h2>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        title: {
            type: String,
            required: true
        }
    }
}
</script>

<style scoped>
.css-heioij {
    overflow: hidden;
    width: 650px;
    background-color: rgb(255, 255, 255);
    border-radius: 16px;
    margin-bottom: 0px;
}

.css-oc8mjz {
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    margin: 0px 20px;
    padding: 10px 0px 5px;
}

.css-eq7f8j {
    display: flex;
    align-items: flex-end;
}

.css-1lmd4kz {
    font-weight: 500;
    font-size: 20px;
    letter-spacing: -0.5px;
    line-height: 28px;
}
</style>