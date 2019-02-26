class Star{
    constructor(){
        this.ratingElement = document.querySelector(".rating");
        this.starCheckBoxes = this.ratingElement.querySelectorAll(".rating_rdo");
        this.starRank = this.ratingElement.querySelector(".star_rank");
        this.addStarCheckBoxClickEvent();
    }
    
    /**
     * @desc star checkbox click event
     */
    addStarCheckBoxClickEvent(){
        this.starCheckBoxes.forEach( starCheck => {
            starCheck.addEventListener("click", event => {
                event.preventDefault();

                this._removeCheckedClass();
                this._addCheckedClass(event.currentTarget.value);
                this._setStarRank(event.currentTarget.value);
            });
        });
    }

    /**
     * @desc checked class remove
     */
    _removeCheckedClass(){
        
        this.starCheckBoxes.forEach( starCheck => {
            console.log(starCheck);
            starCheck.classList.remove("checked");
        });
    }

    /**
     * @desc 체크한 번째 수 만큼 별 채워주기
     * @param {Number} starCheckBoxValue 
     */
    _addCheckedClass(starCheckBoxValue){
        for(let index = 0; index < starCheckBoxValue; index++){
            this.starCheckBoxes[index].classList.add("checked");
        }
    }

    /**
     * @desc star 개수 작성해주기 
     * @param {Number} starCheckBoxValue 
     */
    _setStarRank(starCheckBoxValue){
        this.starRank.innerText = starCheckBoxValue;
    }
};

export default Star;