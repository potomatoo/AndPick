<template>
  <div class="explain-feed">
    <div class="helper-text">피드<i class="mdi mdi-book-open"></i></div>
    <div class="container-feed">
      <div class="one-feed"></div>
      <div class="two-feed"></div>
      <div class="thr-feed"></div>
      <div class="fou-feed"></div>
      <div class="fiv-feed"></div>
    </div>
    <button class="prevbtn-feed" @click="ne">
      <i class="mdi-arrow-feed mdi mdi-arrow-left-bold-circle-outline"></i>
    </button>
    <button class="nextbtn-feed" @click="pl">
      <i class="mdi-arrow-feed mdi mdi-arrow-right-bold-circle-outline"></i>
    </button>
  </div>
</template>

<script>
export default {
  name: "FeedExplain",
  data() {
    return {
      imgList: [
        require("@/assets/feed4.png"),
        require("@/assets/feed5.png"),
        require("@/assets/feed1.png"),
        require("@/assets/feed2.png"),
        require("@/assets/feed3.png")
      ],
      a: 0,
      b: 1,
      c: 2,
      d: 3,
      e: 4
    };
  },
  methods: {
    change() {
      if (this.a < 4) {
        this.a++;
      } else {
        this.a = 0;
      }
      if (this.b < 4) {
        this.b++;
      } else {
        this.b = 0;
      }
      if (this.c < 4) {
        this.c++;
      } else {
        this.c = 0;
      }
      if (this.d < 4) {
        this.d++;
      } else {
        this.d = 0;
      }
      if (this.e < 4) {
        this.e++;
      } else {
        this.e = 0;
      }
    },
    changepr() {
      if (this.a > 0) {
        this.a--;
      } else {
        this.a = 4;
      }
      if (this.b > 0) {
        this.b--;
      } else {
        this.b = 4;
      }
      if (this.c > 0) {
        this.c--;
      } else {
        this.c = 4;
      }
      if (this.d > 0) {
        this.d--;
      } else {
        this.d = 4;
      }
      if (this.e > 0) {
        this.e--;
      } else {
        this.e = 4;
      }
    },
    move() {
      const boxone = document.querySelector(".one-feed");
      const boxtwo = document.querySelector(".two-feed");
      const boxthr = document.querySelector(".thr-feed");
      const boxfou = document.querySelector(".fou-feed");
      const boxfiv = document.querySelector(".fiv-feed");
      const nextbtn = document.querySelector(".nextbtn-feed");
      const prevbtn = document.querySelector(".prevbtn-feed");
      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "one-feed 1s forwards";
      boxtwo.style.animation = "two-feed 1s forwards";
      boxthr.style.animation = "thr-feed 1s forwards";
      boxfou.style.animation = "fou-feed 1s forwards";
      boxfiv.style.animation = "fiv-feed 1s forwards";
    },
    movepr() {
      const boxone = document.querySelector(".one-feed");
      const boxtwo = document.querySelector(".two-feed");
      const boxthr = document.querySelector(".thr-feed");
      const boxfou = document.querySelector(".fou-feed");
      const boxfiv = document.querySelector(".fiv-feed");
      const nextbtn = document.querySelector(".nextbtn-feed");
      const prevbtn = document.querySelector(".prevbtn-feed");

      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "backone-feed 1s forwards";
      boxtwo.style.animation = "backtwo-feed 1s forwards";
      boxthr.style.animation = "backthr-feed 1s forwards";
      boxfou.style.animation = "backfou-feed 1s forwards";
      boxfiv.style.animation = "backfiv-feed 1s forwards";
    },
    stop() {
      const boxone = document.querySelector(".one-feed");
      const boxtwo = document.querySelector(".two-feed");
      const boxthr = document.querySelector(".thr-feed");
      const boxfou = document.querySelector(".fou-feed");
      const boxfiv = document.querySelector(".fiv-feed");
      const nextbtn = document.querySelector(".nextbtn-feed");
      const prevbtn = document.querySelector(".prevbtn-feed");

      boxone.style.animation = "";
      boxtwo.style.animation = "";
      boxthr.style.animation = "";
      boxfou.style.animation = "";
      boxfiv.style.animation = "";

      boxone.style.left = -55 + "%";
      boxone.style.zIndex = 1;
      boxone.style.transform = "scale(0.4)";
      boxone.style.backgroundImage = `url('${this.imgList[this.a]}')`;

      boxtwo.style.left = 15 + "%";
      boxtwo.style.zIndex = 2;
      boxtwo.style.transform = "scale(0.7)";
      boxtwo.style.backgroundImage = `url('${this.imgList[this.b]}')`;

      boxthr.style.left = 85 + "%";
      boxthr.style.zIndex = 3;
      boxthr.style.transform = "scale(1.15)";
      boxthr.style.backgroundImage = `url('${this.imgList[this.c]}')`;

      boxfou.style.left = 155 + "%";
      boxfou.style.zIndex = 2;
      boxfou.style.transform = "scale(0.7)";
      boxfou.style.backgroundImage = `url('${this.imgList[this.d]}')`;

      boxfiv.style.left = 225 + "%";
      boxfiv.style.zIndex = 1;
      boxfiv.style.transform = "scale(0.4)";
      boxfiv.style.backgroundImage = `url('${this.imgList[this.e]}')`;

      nextbtn.removeAttribute("disabled");
      prevbtn.removeAttribute("disabled");
    },
    pl() {
      this.move();
      setTimeout(this.change, 1001);
      setTimeout(this.stop, 1002);
    },
    ne() {
      this.movepr();
      setTimeout(this.changepr, 1001);
      setTimeout(this.stop, 1002);
    }
  }
};
</script>

<style>
.mdi-arrow-feed {
  font-size: 41px;
}

.explain-feed {
  margin-top: 40px;
}
.container-feed {
  position: relative;
  width: 300px;
  height: 450px;
}
.container-feed div {
  width: 550px;
  height: 450px;
  position: absolute;
}
.one-feed {
  background-color: black;
  background-image: url("../../assets/feed4.png");
  background-size: cover;
  transform: scale(0.4);
  left: -55%;
  z-index: 1;
}
.two-feed {
  background-color: black;
  background-image: url("../../assets/feed5.png");
  background-size: cover;
  transform: scale(0.7);
  left: 15%;
  z-index: 2;
}
.thr-feed {
  background-color: black;
  background-image: url("../../assets/feed1.png");
  background-size: cover;
  transform: scale(1.15);
  left: 85%;
  z-index: 3;
}
.fou-feed {
  background-image: url("../../assets/feed2.png");
  background-size: cover;
  transform: scale(0.7);
  left: 155%;
  z-index: 2;
}
.fiv-feed {
  background-color: black;
  background-image: url("../../assets/feed3.png");
  background-size: cover;
  transform: scale(0.4);
  left: 225%;
  z-index: 1;
}
@keyframes one-feed {
  100% {
    transform: scale(0.4);
    left: 225%;
    z-index: 1;
  }
}
@keyframes two-feed {
  100% {
    transform: scale(0.4);
    left: -55%;
    z-index: 1;
  }
}
@keyframes thr-feed {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes fou-feed {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes fiv-feed {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backone-feed {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes backtwo-feed {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backthr-feed {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backfou-feed {
  100% {
    transform: scale(0.4);
    left: 225%;
    z-index: 1;
  }
}
@keyframes backfiv-feed {
  100% {
    transform: scale(0.4);
    left: -55%;
    z-index: 1;
  }
}
</style>
