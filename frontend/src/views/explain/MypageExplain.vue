<template>
  <div class="explain-mypage">
    <div class="helper-text">마이페이지<i class="mdi mdi-book-open"></i></div>
    <div class="container-mypage">
      <div class="one-mypage"></div>
      <div class="two-mypage"></div>
      <div class="thr-mypage"></div>
      <div class="fou-mypage"></div>
      <div class="fiv-mypage"></div>
    </div>
    <button class="prevbtn-mypage" @click="ne">
      <i class="mdi-arrow mdi mdi-arrow-left-bold-circle-outline"></i>
    </button>
    <button class="nextbtn-mypage" @click="pl">
      <i class="mdi-arrow mdi mdi-arrow-right-bold-circle-outline"></i>
    </button>
  </div>
</template>

<script>
export default {
  name: "MypageExplain",
  data() {
    return {
      imgList: [
        require("@/assets/mypage4.png"),
        require("@/assets/mypage5.png"),
        require("@/assets/mypage1.png"),
        require("@/assets/mypage2.png"),
        require("@/assets/mypage3.png")
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
      const boxone = document.querySelector(".one-mypage");
      const boxtwo = document.querySelector(".two-mypage");
      const boxthr = document.querySelector(".thr-mypage");
      const boxfou = document.querySelector(".fou-mypage");
      const boxfiv = document.querySelector(".fiv-mypage");
      const nextbtn = document.querySelector(".nextbtn-mypage");
      const prevbtn = document.querySelector(".prevbtn-mypage");
      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "one-mypage 1s forwards";
      boxtwo.style.animation = "two-mypage 1s forwards";
      boxthr.style.animation = "thr-mypage 1s forwards";
      boxfou.style.animation = "fou-mypage 1s forwards";
      boxfiv.style.animation = "fiv-mypage 1s forwards";
    },
    movepr() {
      const boxone = document.querySelector(".one-mypage");
      const boxtwo = document.querySelector(".two-mypage");
      const boxthr = document.querySelector(".thr-mypage");
      const boxfou = document.querySelector(".fou-mypage");
      const boxfiv = document.querySelector(".fiv-mypage");
      const nextbtn = document.querySelector(".nextbtn-mypage");
      const prevbtn = document.querySelector(".prevbtn-mypage");

      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      boxone.style.animation = "backone-mypage 1s forwards";
      boxtwo.style.animation = "backtwo-mypage 1s forwards";
      boxthr.style.animation = "backthr-mypage 1s forwards";
      boxfou.style.animation = "backfou-mypage 1s forwards";
      boxfiv.style.animation = "backfiv-mypage 1s forwards";
    },
    stop() {
      const boxone = document.querySelector(".one-mypage");
      const boxtwo = document.querySelector(".two-mypage");
      const boxthr = document.querySelector(".thr-mypage");
      const boxfou = document.querySelector(".fou-mypage");
      const boxfiv = document.querySelector(".fiv-mypage");
      const nextbtn = document.querySelector(".nextbtn-mypage");
      const prevbtn = document.querySelector(".prevbtn-mypage");

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
.helper-text {
  font-family: "Do Hyeon", sans-serif;
}
.mdi-arrow {
  font-size: 41px;
}

.explain-mypage {
  margin-top: 40px;
}
.container-mypage {
  position: relative;
  width: 300px;
  height: 450px;
}
.container-mypage div {
  width: 550px;
  height: 450px;
  position: absolute;
}
.one-mypage {
  background-color: black;
  background-image: url("../../assets/mypage4.png");
  background-size: cover;
  transform: scale(0.4);
  left: -55%;
  z-index: 1;
}
.two-mypage {
  background-color: black;
  background-image: url("../../assets/mypage5.png");
  background-size: cover;
  transform: scale(0.7);
  left: 15%;
  z-index: 2;
}
.thr-mypage {
  background-color: black;
  background-image: url("../../assets/mypage1.png");
  background-size: cover;
  transform: scale(1.15);
  left: 85%;
  z-index: 3;
}
.fou-mypage {
  background-color: black;
  background-image: url("../../assets/mypage2.png");
  background-size: cover;
  transform: scale(0.7);
  left: 155%;
  z-index: 2;
}
.fiv-mypage {
  background-color: black;
  background-image: url("../../assets/mypage3.png");
  background-size: cover;
  transform: scale(0.4);
  left: 225%;
  z-index: 1;
}
@keyframes one-mypage {
  100% {
    transform: scale(0.4);
    left: 225%;
    z-index: 1;
  }
}
@keyframes two-mypage {
  100% {
    transform: scale(0.4);
    left: -55%;
    z-index: 1;
  }
}
@keyframes thr-mypage {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes fou-mypage {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes fiv-mypage {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backone-mypage {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes backtwo-mypage {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backthr-mypage {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backfou-mypage {
  100% {
    transform: scale(0.4);
    left: 225%;
    z-index: 1;
  }
}
@keyframes backfiv-mypage {
  100% {
    transform: scale(0.4);
    left: -55%;
    z-index: 1;
  }
}
</style>
