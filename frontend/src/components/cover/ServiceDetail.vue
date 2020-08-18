<template>
  <div class="detail">
    <div class="outline feed row" style="background-color:#df3277">
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-if="showFeed">피드</h2>
        </transition>
        <transition name="slide-feed">
          <p v-if="showFeed">
            유용한 뉴스나 기술 블로그의 정보를 구독하고 싶은가요? 그렇다면 지금
            당장 피드에 원하는 페이지를 추가해보세요. 실시간으로 업데이트되는
            정보를 받아볼 수 있습니다!
          </p>
        </transition>
        <transition name="slide-feed">
          <p v-if="showFeed">
            피드에 폴더를 만든 후 페이지를 추가해서 이용할 수 있습니다.
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
      <div class="col-6">
        <img
          class="detail-image"
          src="https://cdn.pixabay.com/photo/2020/01/26/19/32/architecture-4795667_1280.jpg"
        />
      </div>
    </div>

    <div class="outline board row" style="background-color:#00d59b">
      <div class="col-6">
        <img
          class="detail-image"
          src="https://cdn.pixabay.com/photo/2020/01/26/19/32/architecture-4795667_1280.jpg"
        />
      </div>
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-if="showBoard">보드</h2>
        </transition>
        <transition name="slide-feed">
          <p v-if="showBoard">
            구독하는 피드에 올라오는 정보중에서 나중에 다시 보고 싶은 것이
            있으신가요? 보드 기능을 이용하면 원하는 글을 저장할 수 있습니다.
            나중에 보고 싶을 때 다시 꺼내보는 건 어떨까요ㅇ오오오오오오
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
    </div>

    <div class="outline mypage row" style="background-color:#ad249f">
      <div class="col-1"></div>
      <div class="inline col-4">
        <transition name="slide-feed">
          <h2 v-if="showMy">마이페이지</h2>
        </transition>
        <transition name="slide-feed">
          <p v-if="showMy">
            마이페이지는 에디터를 이용하여 기사를 스크랩하고 나만의 생각을
            정리할 수 있는 공간이에요. 해시태그 기능을 이용해서 같은 주제의
            게시글들을 따로 모아서 볼 수도 있습니다. 면접 준비 및 같은 주제의
            게시글을 편리하게 볼 수 있습니다.
          </p>
        </transition>
      </div>
      <div class="col-1"></div>
      <div class="col-6">
        <img
          class="detail-image"
          src="https://cdn.pixabay.com/photo/2020/01/26/19/32/architecture-4795667_1280.jpg"
        />
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
  color: white;
  height: 400px;
  /* word-break: keep-all; */
  padding: 25px 30px 25px 25px;
  overflow: auto;
}
.inline {
  padding: 40px 0px 40px 0px;
}
.outline > div > h2 {
  font-family: "Black Han Sans", sans-serif;
}
.outline > div > p {
  font-size: 20px;
  color: white;
  /* font-family: "Black Han Sans", sans-serif; */
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
