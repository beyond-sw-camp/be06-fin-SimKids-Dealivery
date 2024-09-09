<template>
    <div class="css-ws3ctz ezs8n5y0">
        <div type="RANDOM_COLLECTION_NUMBER" class="css-1ytvbby ezs8n5y4">
            <span class="css-vonb0q ezs8n5y2">
                {{ title }}
            </span>
            <span class="css-1dms2ey ezs8n5y1">
                {{ subtitle }}
            </span>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        title: {
            type: String,
            default: '🎉 특가 가득!',
        },
        subtitle: {
            type: String,
            default: '놓치지 말고 지금 주문하세요!',
        },
    },
}
</script>


<style scoped>
.css-ws3ctz {
    padding: 16px;
    display: flex;
    -webkit-box-pack: justify;
    justify-content: space-between;
    margin-top: 50px;
}

.css-1ytvbby {
    display: flex;
    -webkit-box-align: center;
    align-items: center;
    flex-direction: column;
    width: 100%;
    gap: 10px;
    padding-bottom: 8px
}

.css-vonb0q {
    text-overflow: ellipsis;
    color: rgb(34, 34, 34);
    font-size: 28px;
    line-height: 31px;
    font-weight: 500;
    display: -webkit-box;
    overflow: hidden;
    word-break: break-all;
    white-space: normal;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}

.css-1dms2ey {
    text-overflow: ellipsis;
    font-size: 16px;
    color: rgb(132, 143, 154);
    line-height: 18px;
    margin-top: 4px;
    display: -webkit-box;
    overflow: hidden;
    word-break: break-all;
    white-space: normal;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}
</style>