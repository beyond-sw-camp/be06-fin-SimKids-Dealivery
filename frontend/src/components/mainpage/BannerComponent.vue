<template>
    <div class="slideshow-container">
        <div class="slides" :style="{ transform: `translateX(-${currentSlide * 100}%)`, transitionDuration }">
            <!-- 마지막 슬라이드를 가짜로 첫번째 앞에 배치 -->
            <div class="slide">
                <img :src="slides[slides.length - 1].src" alt="Slide image">
            </div>
            <div v-for="(slide, index) in slides" :key="index" class="slide">
                <img :src="slide.src" alt="Slide image">
            </div>
            <!-- 첫번째 슬라이드를 가짜로 마지막 뒤에 배치 -->
            <div class="slide">
                <img :src="slides[0].src" alt="Slide image">
            </div>
        </div>
        <a class="prev" @click="prevSlide">&#10094;</a>
        <a class="next" @click="nextSlide">&#10095;</a>
    </div>
</template>

<script>
export default {
    data() {
        return {
            currentSlide: 1, // 슬라이드가 가짜 첫 슬라이드에서 시작
            slides: [
                { src: require('@/assets/banner1.png') },
                { src: require('@/assets/banner2.png') },
                { src: require('@/assets/banner3.png') },
                { src: require('@/assets/banner4.png') },
            ],
            interval: null, // 자동 슬라이드 넘김을 위한 interval 변수
            transitionDuration: '0.5s' // 슬라이드 전환 시간
        };
    },
    mounted() {
        this.startSlideshow(); // 컴포넌트가 마운트되면 슬라이드쇼 시작
    },
    beforeUnmount() {
        this.stopSlideshow(); // 컴포넌트가 언마운트되면 슬라이드쇼 중지
    },
    methods: {
        startSlideshow() {
            this.interval = setInterval(this.nextSlide, 6000); // 6초마다 슬라이드 변경
        },
        stopSlideshow() {
            clearInterval(this.interval);
        },
        prevSlide() {
            if (this.currentSlide === 0) {
                // 첫번째 슬라이드일 때 가짜 마지막 슬라이드로 순간 이동
                this.transitionDuration = '0s';
                this.currentSlide = this.slides.length;
                setTimeout(() => {
                    this.transitionDuration = '0.5s';
                    this.currentSlide--;
                }, 50);
            } else {
                this.currentSlide--;
            }
        },
        nextSlide() {
            if (this.currentSlide === this.slides.length + 1) {
                // 마지막 슬라이드일 때 가짜 첫번째 슬라이드로 순간 이동
                this.transitionDuration = '0s';
                this.currentSlide = 1;
                setTimeout(() => {
                    this.transitionDuration = '0.5s';
                    this.currentSlide++;
                }, 50);
            } else {
                this.currentSlide++;
            }
        }
    }
};
</script>

<style scoped>
.slideshow-container {
    max-width: 1440px;
    position: relative;
    margin: auto;
    margin-top: 10px;
    overflow: hidden;
}

.slides {
    display: flex;
    transition: transform 0.5s ease-in-out;
}

.slide {
    min-width: 100%;
}

img {
    width: 100%;
    display: block;
}

/* 슬라이드 버튼 초기 상태 (보이지 않음) */
.prev,
.next {
    cursor: pointer;
    position: absolute;
    top: 50%;
    width: auto;
    padding: 16px;
    margin-top: -22px;
    color: white;
    font-weight: bold;
    font-size: 18px;
    transition: opacity 0.3s ease;
    opacity: 0;
    pointer-events: none;
}

.next {
    right: 0;
    border-radius: 3px 0 0 3px;
}

.slideshow-container:hover .prev,
.slideshow-container:hover .next {
    opacity: 1;
    pointer-events: auto;
}

.prev:hover,
.next:hover {
    background-color: transparent;
}
</style>
