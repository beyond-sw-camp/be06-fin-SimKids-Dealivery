<template>
    <HeaderComponent></HeaderComponent>
    <div class="page-wrapper">
        <div class="page-layout">
            <MypageAsideComponent @menu-click="handleMenuClick"></MypageAsideComponent>
            <div class="content-wrapper">
                <TitleComponent :title="currentTitle" />
                <router-view></router-view> <!-- 하위 라우터 컴포넌트가 제목 아래에 표시됩니다. -->
            </div>
        </div>
    </div>
    <FooterComponent></FooterComponent>
</template>

<script>
import HeaderComponent from '@/components/common/HeaderComponent.vue';
import MypageAsideComponent from '@/components/mypage/MypageAsideComponent.vue';
import FooterComponent from '@/components/common/FooterComponent.vue';
import TitleComponent from '@/components/mypage/TitleComponent.vue';

export default {
    name: 'MyPage',
    components: {
        HeaderComponent,
        MypageAsideComponent,
        TitleComponent,
        FooterComponent,
    },
    data() {
        return {
            currentTitle: '주문 내역', // 기본 Title로 설정
        };
    },
    methods: {
        handleMenuClick(menu) {
            if (menu === 'order') {
                this.currentTitle = '주문 내역';
                this.$router.push('/mypage/order');
            } else if (menu === 'qna') {
                this.currentTitle = 'My 문의';
                this.$router.push('/mypage/qna');
            }
        }
    }
}
</script>

<style scoped>
.page-wrapper {
    position: relative;
    min-width: 1050px;
    background-color: rgb(242, 245, 248);
    padding: 20px 0;
}

.page-layout {
    display: flex;
    align-items: flex-start;
    gap: 20px;
    justify-content: center;
    padding: 50px 0px 70px 100px;
    margin: 0 auto;
    /* 좌우 중앙 정렬 */
    max-width: 1150px;
    /* 최대 너비 설정 */
    width: 100%;
    /* 부모 컨테이너 너비 채우기 */
}

.content-wrapper {
    display: flex;
    flex-direction: column;
    gap: 20px;
    /* 제목과 하위 컴포넌트 사이 여백 추가 */
    width: 100%;
}
</style>
