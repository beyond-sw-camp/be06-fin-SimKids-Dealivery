<template>
    <HeaderComponent></HeaderComponent>
    <div class="css-u71x2d eug5r8l4">
        <div class="css-72lz6z eug5r8l3">
            <MypageAsideComponent></MypageAsideComponent>
                <MypageOrderListComponent></MypageOrderListComponent>
        </div>
    </div>
    <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import MypageAsideComponent from '@/components/mypage/MypageAsideComponent.vue';
import MypageOrderListComponent from '@/components/mypage/MypageOrderListComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';

export default {
    name: 'MyPage',
    components: {
        HeaderComponent,
        MypageAsideComponent,
        MypageOrderListComponent,
        FooterComponent,
    },
    methods: {
        toggleDetails() {
            this.isDetailsVisible = !this.isDetailsVisible;
            this.isArrowRotated = !this.isArrowRotated; 
        }
    }
}
</script>

<style scoped>
.css-u71x2d {
    position: relative;
    min-width: 1050px;
    background-color: rgb(242, 245, 248);
    padding: 20px 0;
}

.css-72lz6z {
    display: flex;
    align-items: flex-start;
    gap: 20px;
    justify-content: center;
    padding: 50px 0px 80px;
    margin: 0px auto;
}
</style>
