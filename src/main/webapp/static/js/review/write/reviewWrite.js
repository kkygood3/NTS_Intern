'use strict';

import Star from "./star"
import ReviewContents from "./reviewContents"
import ReviewPicture from "./reviewPicture"
import ReviewRegist from "./reviewRegist"

document.addEventListener("DOMContentLoaded" , () => {
    const star = new Star();
    const reviewContents = new ReviewContents();
    const reviewPicture = new ReviewPicture();
    const reviewRegist = new ReviewRegist(star.starRank, reviewContents.reviewTextArea ,reviewPicture.fileList);
});

