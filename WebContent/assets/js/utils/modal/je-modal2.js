"use strict";
/*
 * @author Jerson Ramírez Ortiz
 * @Date 04/04/2020 14:20 - update 
 */
var __extends = (this && this.__extends) || (function () {
    var extendStatics = function (d, b) {
        extendStatics = Object.setPrototypeOf ||
            ({
                    __proto__: []
                }
                instanceof Array && function (d, b) {
                    d.__proto__ = b;
                }) ||
            function (d, b) {
                for (var p in b)
                    if (b.hasOwnProperty(p)) d[p] = b[p];
            };
        return extendStatics(d, b);
    };
    return function (d, b) {
        extendStatics(d, b);

        function __() {
            this.constructor = d;
        }
        d.prototype = b === null ? Object.create(b) : (__.prototype = b.prototype, new __());
    };
})();
var Modal = /** @class */ (function () {
    function Modal(obj) {
        this.body_container = obj.body || null;
        this.header_container = obj.header || null;
        this.footer_container = obj.footer || null;
        this.animation_open = obj.animation_open || "animate_default_open 2s 1 ease";
        this.animation_close = obj.animation_close || "animate_default_close 2s 1 ease";
        this.time_close = obj.time_close || 2000;
        this.automatic_close = obj.automatic_close || false;
        this.timeout_modal = obj.timeout_modal || 10000;
        this.max_width = obj.max_width || '480px';
        this.modal_container = null;
        this.closeAuto_timeout = null;
    }
    Modal.prototype.open = function () {
        var _this = this;
        this.modal_container = this.createElement();
        this.modal_container.firstElementChild.style.animation = "" + this.animation_open;
        document.body.appendChild(this.modal_container);
        if (this.automatic_close) {
            this.closeAuto_timeout = setTimeout(function () {
                _this.closeAnimation();
            }, this.timeout_modal);
        }
        //add listener
        this.close();
    };
    Modal.prototype.close = function () {
        var _this = this;
        var evet = function (e) {
            if (e.target === _this.modal_container || e.target === _this.modal_container.firstElementChild) {
            	if(_this.closeAuto_timeout){
                    clearTimeout(_this.closeAuto_timeout);
                }
                _this.closeAnimation();
                _this.modal_container.removeEventListener('click', evet);
            }
        };
        this.modal_container.addEventListener('click', evet);
    };
    Modal.prototype.closeAnimation = function () {
        var _this = this;
        /*console.log(this.animation_close);*/
        this.modal_container
            .firstElementChild
            .style
            .animation = this.animation_close;
        setTimeout(function () {
        	try{
        		document.body.removeChild(_this.modal_container);
        	}catch(e){}
        }, this.time_close);
    };
    return Modal;
}());
var MessageModal = /** @class */ (function (_super) {
    __extends(MessageModal, _super);

    function MessageModal(obj) {
        var _this = _super.call(this, obj) || this;
        _this.position = obj.position || {
            "x": "left",
            "y": "top"
        };
        _this.time_close = 2000;
        return _this;
    }
    /*
        Devuelve un elemento div
     */
    MessageModal.prototype.createElement = function () {
        var element = document.createElement("div");
        element.setAttribute("class", "je-modal-container je-modal-container_message");
        var close = document.createElement("div");
        close.innerHTML = "x";
        close.classList.add("je-modal-close");
        this.addListenerCloseMessage(close);
        if (this.position) {
            if (this.position.x === 'left') {
                element.style.left = "0";
            } else {
                element.style.right = "0";
            }
            if (this.position.y === 'top') {
                element.style.top = "1rem";
            } else {
                element.style.bottom = "0rem";
                element.style.top = "initial";
                /*element.style.top = "auto";*/
            }
        }
        element.innerHTML = `
        			<div class="je-modal_inner">
        				<div class="je-modal_inner-wrapper" style="max-width:${this.max_width }">
        					${this.body_container}            
        				</div>         
        			</div>
        `;
        element.appendChild(close);
        return element;
    };
    MessageModal.prototype.addListenerCloseMessage = function (close) {
        var _this = this;
        close.addEventListener('click', function (e) {
            _this.closeAnimation();
        });
    };
    return MessageModal;
}(Modal));
var BigModal = /** @class */ (function (_super) {
    __extends(BigModal, _super);

    function BigModal(obj) {
        return _super.call(this, obj) || this;
    }
    BigModal.prototype.createElement = function () {
        var element = document.createElement("div");
        element.classList.add("je-modal-container");
        element.innerHTML = "\n          <div class=\"je-modal_inner\">\n            <div class=\"je-modal_inner-wrapper\" style=\"max-width:" + this.max_width + "\">\n            </div>\n          </div> \n        ";
        var wrapper = element.querySelector(".je-modal_inner-wrapper");
        if (this.header_container) {
            wrapper.innerHTML += "\n                <div class=\"je-modal-wrapper_header\">\n                    " + this.header_container + "\n                </div>\n            ";
        }
        if (this.body_container) {
        	if(this.body_container.nodeType){
        		let modalWrapper = document.createElement("div");
        		modalWrapper.classList.add("je-modal-wrapper_body");
        		modalWrapper.appendChild(this.body_container);
        		wrapper.appendChild(modalWrapper);
        	}else{
        		wrapper.innerHTML +=`
                	<div class="je-modal-wrapper_body">
                		${this.body_container}
                	</div>
                `;
        	}            
        }
        if (this.footer_container) {
            wrapper.innerHTML += "\n            <div class=\"je-modal-wrapper_footer\">\n                " + this.footer_container + "\n            </div>\n            ";
        }
        return element;
    };
    return BigModal;
}(Modal));