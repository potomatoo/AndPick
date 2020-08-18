<template>
  <div class="explain-board">
    <div class="helper-text">보드<i class="mdi mdi-book-open"></i></div>
    <div class="container-board">
      <!-- <div class="one"></div> -->
      <div class="two-board"></div>
      <div class="thr-board"></div>
      <div class="fou-board"></div>
      <!-- <div class="fiv"></div> -->
    </div>
    <button class="prevbtn-board" @click="ne">
      <i class="mdi-arrow-board mdi mdi-arrow-left-bold-circle-outline"></i>
    </button>
    <button class="nextbtn-board" @click="pl">
      <i class="mdi-arrow mdi mdi-arrow-right-bold-circle-outline"></i>
    </button>
  </div>
</template>

<script>
export default {
  name: "BoardExplain",
  data() {
    return {
      imgList: [
        // require("@/assets/4.png"),
        require("@/assets/board3.png"),
        require("@/assets/board1.png"),
        require("@/assets/board2.png")
        // require("@/assets/3.png")
      ],
      a: 0,
      b: 1,
      c: 2
      // d: 3,
      // e: 4
    };
  },
  methods: {
    change() {
      if (this.a < 2) {
        this.a++;
      } else {
        this.a = 0;
      }
      if (this.b < 2) {
        this.b++;
      } else {
        this.b = 0;
      }
      if (this.c < 2) {
        this.c++;
      } else {
        this.c = 0;
      }
      // if (this.d < 4) {
      //   this.d++;
      // } else {
      //   this.d = 0;
      // }
      // if (this.e < 4) {
      //   this.e++;
      // } else {
      //   this.e = 0;
      // }
    },
    changepr() {
      if (this.a > 0) {
        this.a--;
      } else {
        this.a = 2;
      }
      if (this.b > 0) {
        this.b--;
      } else {
        this.b = 2;
      }
      if (this.c > 0) {
        this.c--;
      } else {
        this.c = 2;
      }
      // if (this.d > 0) {
      //   this.d--;
      // } else {
      //   this.d = 4;
      // }
      // if (this.e > 0) {
      //   this.e--;
      // } else {
      //   this.e = 4;
      // }
    },
    move() {
      // const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      // const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");
      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      // boxone.style.animation = "one 1s forwards";
      boxtwo.style.animation = "two-board 1s forwards";
      boxthr.style.animation = "thr-board 1s forwards";
      boxfou.style.animation = "fou-board 1s forwards";
      // boxfiv.style.animation = "fiv 1s forwards";
    },
    movepr() {
      // const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      // const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");

      nextbtn.disabled = "disabled";
      prevbtn.disabled = "disabled";
      // boxone.style.animation = "backone 1s forwards";
      boxtwo.style.animation = "backtwo-board 1s forwards";
      boxthr.style.animation = "backthr-board 1s forwards";
      boxfou.style.animation = "backfou-board 1s forwards";
      // boxfiv.style.animation = "backfiv 1s forwards";
    },
    stop() {
      // const boxone = document.querySelector(".one");
      const boxtwo = document.querySelector(".two-board");
      const boxthr = document.querySelector(".thr-board");
      const boxfou = document.querySelector(".fou-board");
      // const boxfiv = document.querySelector(".fiv");
      const nextbtn = document.querySelector(".nextbtn-board");
      const prevbtn = document.querySelector(".prevbtn-board");

      // boxone.style.animation = "";
      boxtwo.style.animation = "";
      boxthr.style.animation = "";
      boxfou.style.animation = "";
      // boxfiv.style.animation = "";

      // boxone.style.left = -55 + "%";
      // boxone.style.zIndex = 1;
      // boxone.style.transform = "scale(0.5)";
      // boxone.style.backgroundImage = `url('${this.imgList[this.a]}')`;

      boxtwo.style.left = 15 + "%";
      boxtwo.style.zIndex = 2;
      boxtwo.style.transform = "scale(0.7)";
      boxtwo.style.backgroundImage = `url('${this.imgList[this.a]}')`;

      boxthr.style.left = 85 + "%";
      boxthr.style.zIndex = 3;
      boxthr.style.transform = "scale(1.15)";
      boxthr.style.backgroundImage = `url('${this.imgList[this.b]}')`;

      boxfou.style.left = 155 + "%";
      boxfou.style.zIndex = 2;
      boxfou.style.transform = "scale(0.7)";
      boxfou.style.backgroundImage = `url('${this.imgList[this.c]}')`;

      // boxfiv.style.left = 225 + "%";
      // boxfiv.style.zIndex = 1;
      // boxfiv.style.transform = "scale(0.5)";
      // boxfiv.style.backgroundImage = `url('${this.imgList[this.e]}')`;

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
.mdi-arrow-board {
  font-size: 41px;
}

.explain-board {
  margin-top: 40px;
}
.container-board {
  position: relative;
  width: 300px;
  height: 450px;
}
.container-board div {
  width: 550px;
  height: 450px;
  position: absolute;
}
/* .one {
  background-image: url("../../assets/4.png");
  background-size: cover;
  transform: scale(0.5);
  left: -55%;
  z-index: 1;
} */
.two-board {
  background-image: url("../../assets/board3.png");
  background-size: cover;
  transform: scale(0.7);
  left: 15%;
  z-index: 2;
}
.thr-board {
  background-image: url("../../assets/board1.png");
  background-size: cover;
  transform: scale(1.15);
  left: 85%;
  z-index: 3;
}
.fou-board {
  background-image: url("../../assets/board2.png");
  background-size: cover;
  transform: scale(0.7);
  left: 155%;
  z-index: 2;
}
/* .fiv {
  background-image: url("../../assets/3.png");
  background-size: cover;
  transform: scale(0.5);
  left: 225%;
  z-index: 1;
} */
/* @keyframes one {
  100% {
    transform: scale(0.5);
    left: 225%;
    z-index: 1;
  }
} */
@keyframes two-board {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes thr-board {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
@keyframes fou-board {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
/* @keyframes fiv {
  100% {
    transform: scale(0.8);
    left: 155%;
    z-index: 2;
  }
} */
/* @keyframes backone {
  100% {
    transform: scale(0.8);
    left: 15%;
    z-index: 2;
  }
} */
@keyframes backtwo-board {
  100% {
    transform: scale(1.15);
    left: 85%;
    z-index: 3;
  }
}
@keyframes backthr-board {
  100% {
    transform: scale(0.7);
    left: 155%;
    z-index: 2;
  }
}
@keyframes backfou-board {
  100% {
    transform: scale(0.7);
    left: 15%;
    z-index: 2;
  }
}
/* @keyframes backfiv {
  100% {
    transform: scale(0.5);
    left: -55%;
    z-index: 1;
  }
} */
</style>
