package com.j.best.user.application.service.rule;


import com.j.best.user.domain.vo.ActivityApplyContext;


public class ApplyRuleEngine {


    public static class Builder {
        private IRule head;
        private IRule tail;


        public Builder addRule(IRule rule) {
            if (this.head == null) {
                this.head = this.tail = rule;
                return this;
            }
            this.tail.next(rule);
            this.tail = rule;

            return this;
        }

        public IRule build() {
            return this.head;
        }
    }



}
