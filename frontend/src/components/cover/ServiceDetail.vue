<template>
  <div class="detail">
    <div class="outline feed row">
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-show="showFeed">피드</h2>
        </transition>
        <transition name="slide-feed">
          <p v-show="showFeed">
            유용한 뉴스나 기술 블로그의 정보를 구독하고 싶은가요? 그렇다면 지금
            당장 피드에 원하는 채널을 추가해보세요. <br />
            실시간으로 업데이트되는 정보를 받아볼 수 있을거에요!
            <br /><br />
            카테고리 별로 피드를 생성하여 이용해보세요!
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
      <div class="col-6">
        <img class="detail-image" src="@/assets/test1.png" />
      </div>
    </div>

    <div class="outline board row">
      <div class="col-6">
        <img class="detail-image" src="@/assets/test2.png" />
      </div>
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-show="showBoard">보드</h2>
        </transition>
        <transition name="slide-feed">
          <p v-show="showBoard">
            구독하고 있는 채널의 기사를 저장하고 싶으신가요?<br />그렇다면 지금
            당장 보드에 해당 기사를 추가해보세요.<br />
            저장된 기사는 시간이 지나도 다시 볼 수 있을거에요!
            <br /><br />
            폴더를 생성하여 입맛대로 저장해보세요!
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
    </div>

    <div class="outline mypage row">
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-show="showMy">마이페이지</h2>
        </transition>
        <transition name="slide-feed">
          <p v-show="showMy">
            지금 보고 있는 기사에 자신의 생각을 더하고 싶으신가요?<br />
            그렇다면 지금 당장 마이페이지를 활용해보세요.<br />
            다양한 편집기능과 함께 자신의 글로 만들 수 있을거에요!
            <br /><br />
            나만의 공간을 만들고 해시태그와 함께 이용해보세요!
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
      <div class="col-6">
        <img class="detail-image" src="@/assets/test3.png" />
      </div>
    </div>
  </div>
</template>
<script src="https://cdnjs.cloudflare.com/ajax/libs/scrollmonitor/1.2.0/scrollMonitor.js"></script>
<script>
export default {
  name: "ServiceDetail",

  data() {
    return {
      showFeed: false,
      showBoard: false,
      showMy: false
    };
  },
  methods: {
    scrollWatcher() {
      const scrollMonitor = require("scrollmonitor");
      const feedElement = document.querySelector(".feed");
      const boardElement = document.querySelector(".board");
      const mypageElement = document.querySelector(".mypage");
      const feedWatcher = scrollMonitor.create(feedElement);
      const boardWatcher = scrollMonitor.create(boardElement);
      const mypageWatcher = scrollMonitor.create(mypageElement);
      feedWatcher.enterViewport(() => {
        this.showFeed = true;
      });
      boardWatcher.enterViewport(() => {
        this.showBoard = true;
      });
      mypageWatcher.enterViewport(() => {
        this.showMy = true;
      });
    }
  },

  mounted() {
    this.scrollWatcher();
  }
};
</script>

<style>
.outline {
  padding: 0px 30px 0px 25px;
}
.inline {
  padding: 40px 0px 40px 0px;
}
.outline > div > h2 {
  font-family: "Black Han Sans", sans-serif;
}
.outline > div > p {
  font-size: 20px;
}
.detail-image {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  /* text-overflow: clip; */
}
.slide-feed-enter-active {
  transition: all 2s ease;
}
.slide-feed-enter {
  transform: translateY(150px);
  opacity: 0;
}
</style>
