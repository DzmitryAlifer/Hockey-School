package by.epam.hockeyschool.navigation;

import by.epam.hockeyschool.navigation.commands.*;

/**
 * Created by Dmitry Olifer on 19.06.2015.
 *
 * The enumeration class {@code Commands} includes all the command
 * names. Each command name conducts to a definite class, that
 * realizes its own actions.
 */
public enum Commands {

    LANGUAGE {
        /**
         * Conducts to {@code LanguageCommand} class.
         * @return  an instance of {@code LanguageCommand} class.
         */
        @Override
        public Command getCommand() {
            return new LanguageCommand();
        }
    },

    LOGIN {
        /**
         * Conducts to {@code LoginCommand} class.
         * @return  an instance of {@code LoginCommand} class.
         */
        @Override
        public Command getCommand() {
            return new LoginCommand();
        }
    },

    LOGOUT {
        /**
         * Conducts to {@code LogoutCommand} class.
         * @return  an instance of {@code LogoutCommand} class.
         */
        @Override
        public Command getCommand() {
            return new LogoutCommand();
        }
    },

    SUMMARY {
        /**
         * Conducts to {@code SummaryCommand} class.
         * @return  an instance of {@code SummaryCommand} class.
         */
        @Override
        public Command getCommand() {
            return new SummaryCommand();
        }
    },

    BUDGET {
        /**
         * Conducts to {@code BudgetCommand} class.
         * @return  an instance of {@code BudgetCommand} class.
         */
        @Override
        public Command getCommand() {
            return new BudgetCommand();
        }
    },

    ASK_FOR_FUNDS {
        /**
         * Conducts to {@code AskForFundsCommand} class.
         * @return  an instance of {@code AskForFundsCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AskForFundsCommand();
        }
    },

    LETTER {
        /**
         * Conducts to {@code LetterCommand} class.
         * @return  an instance of {@code LetterCommand} class.
         */
        @Override
        public Command getCommand() {
            return new LetterCommand();
        }
    },

    ANSWER {
        /**
         * Conducts to {@code AnswerCommand} class.
         * @return  an instance of {@code AnswerCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AnswerCommand();
        }
    },

    SPEND_MONEY {
        /**
         * Conducts to {@code SpendMoneyCommand} class.
         * @return  an instance of {@code SpendMoneyCommand} class.
         */
        @Override
        public Command getCommand() {
            return new SpendMoneyCommand();
        }
    },

    SPEND_MONEY_RESULT {
        /**
         * Conducts to {@code SpendMoneyResultCommand} class.
         * @return  an instance of {@code SpendMoneyResultCommand} class.
         */
        @Override
        public Command getCommand() {
            return new SpendMoneyResultCommand();
        }
    },

    ADD_PLAYER {
        /**
         * Conducts to {@code AddPlayerCommand} class.
         * @return  an instance of {@code AddPlayerCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AddPlayerCommand();
        }
    },

    PLAYER_TO_BASE {
        /**
         * Conducts to {@code PlayerToBaseCommand} class.
         * @return  an instance of {@code PlayerToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PlayerToBaseCommand();
        }
    },

    DELETE_PLAYER {
        /**
         * Conducts to {@code DeletePlayerCommand} class.
         * @return  an instance of {@code DeletePlayerCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeletePlayerCommand();
        }
    },

    DELETE_PLAYER_FROM_BASE {
        /**
         * Conducts to {@code DeletePlayerBaseCommand} class.
         * @return  an instance of {@code DeletePlayerBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeletePlayerBaseCommand();
        }
    },

    PROMOTE_PLAYER {
        /**
         * Conducts to {@code PromotePlayerCommand} class.
         * @return  an instance of {@code PromotePlayerCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PromotePlayerCommand();
        }
    },

    PROMOTE_PLAYER_IN_BASE {
        /**
         * Conducts to {@code PromotePlayerBaseCommand} class.
         * @return  an instance of {@code PromotePlayerBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PromotePlayerBaseCommand();
        }
    },

    QUEST_PLAYER_LIST {
        /**
         * Conducts to {@code QuestPlayerListCommand} class.
         * @return  an instance of {@code QuestPlayerListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new QuestPlayerListCommand();
        }
    },

    QUEST_FORM {
        /**
         * Conducts to {@code QuestFormCommand} class.
         * @return  an instance of {@code QuestFormCommand} class.
         */
        @Override
        public Command getCommand() {
            return new QuestFormCommand();
        }
    },

    QUEST_TO_BASE {
        /**
         * Conducts to {@code QuestToBaseCommand} class.
         * @return  an instance of {@code QuestToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new QuestToBaseCommand();
        }
    },

    UPDATE_QUEST {
        /**
         * Conducts to {@code UpdateQuestCommand} class.
         * @return  an instance of {@code QuestToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new UpdateQuestCommand();
        }
    },

    SET_PLAYER_PRICE {
        /**
         * Conducts to {@code SetPlayerPriceCommand} class.
         * @return  an instance of {@code SetPlayerPriceCommand} class.
         */
        @Override
        public Command getCommand() {
            return new SetPlayerPriceCommand();
        }
    },

    CHOOSE_PLAYER_PRICE {
        /**
         * Conducts to {@code ChoosePlayerPriceCommand} class.
         * @return  an instance of {@code ChoosePlayerPriceCommand} class.
         */
        @Override
        public Command getCommand() {
            return new ChoosePlayerPriceCommand();
        }
    },

    PLAYER_PRICE_TO_BASE {
        /**
         * Conducts to {@code PlayerPriceToBaseCommand} class.
         * @return  an instance of {@code PlayerPriceToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PlayerPriceToBaseCommand();
        }
    },


    SELL_PLAYER_LIST {
        /**
         * Conducts to {@code SellPlayerListCommand} class.
         * @return  an instance of {@code SellPlayerListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new SellPlayerListCommand();
        }
    },

    PLAYER_LIST {
        /**
         * Conducts to {@code PlayerListCommand} class.
         * @return  an instance of {@code PlayerListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PlayerListCommand();
        }
    },

    ADD_COACH {
        /**
         * Conducts to {@code AddCoachCommand} class.
         * @return  an instance of {@code AddCoachCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AddCoachCommand();
        }
    },

    COACH_TO_BASE {
        /**
         * Conducts to {@code CoachToBaseCommand} class.
         * @return  an instance of {@code CoachToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachToBaseCommand();
        }
    },

    DELETE_COACH {
        /**
         * Conducts to {@code DeleteCoachCommand} class.
         * @return  an instance of {@code DeleteCoachCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeleteCoachCommand();
        }
    },

    DELETE_COACH_FROM_BASE {
        /**
         * Conducts to {@code DeleteCoachBaseCommand} class.
         * @return  an instance of {@code DeleteCoachBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeleteCoachBaseCommand();
        }
    },

    COACH_LIST {
        /**
         * Conducts to {@code CoachListCommand} class.
         * @return  an instance of {@code CoachListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachListCommand();
        }
    },

    ADD_PHYSIO {
        /**
         * Conducts to {@code AddPhysioCommand} class.
         * @return  an instance of {@code AddPhysioCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AddPhysioCommand();
        }
    },

    PHYSIO_TO_BASE {
        /**
         * Conducts to {@code PhysioToBaseCommand} class.
         * @return  an instance of {@code PhysioToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PhysioToBaseCommand();
        }
    },

    DELETE_PHYSIO {
        /**
         * Conducts to {@code DeletePhysioCommand} class.
         * @return  an instance of {@code DeletePhysioCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeletePhysioCommand();
        }
    },

    DELETE_PHYSIO_FROM_BASE {
        /**
         * Conducts to {@code DeletePhysioBaseCommand} class.
         * @return  an instance of {@code DeletePhysioBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeletePhysioBaseCommand();
        }
    },

    PHYSIO_LIST {
        /**
         * Conducts to {@code PhysioListCommand} class.
         * @return  an instance of {@code PhysioListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PhysioListCommand();
        }
    },

    ADD_ADMIN {
        /**
         * Conducts to {@code AddAdminCommand} class.
         * @return  an instance of {@code AddAdminCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AddAdminCommand();
        }
    },

    ADMIN_TO_BASE {
        /**
         * Conducts to {@code AdminToBaseCommand} class.
         * @return  an instance of {@code AdminToBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AdminToBaseCommand();
        }
    },

    DELETE_ADMIN {
        /**
         * Conducts to {@code DeleteAdminCommand} class.
         * @return  an instance of {@code DeleteAdminCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeleteAdminCommand();
        }
    },

    DELETE_ADMIN_FROM_BASE {
        /**
         * Conducts to {@code DeleteAdminBaseCommand} class.
         * @return  an instance of {@code DeleteAdminBaseCommand} class.
         */
        @Override
        public Command getCommand() {
            return new DeleteAdminBaseCommand();
        }
    },

    ADMIN_LIST {
        /**
         * Conducts to {@code AdminListCommand} class.
         * @return  an instance of {@code AdminListCommand} class.
         */
        @Override
        public Command getCommand() {
            return new AdminListCommand();
        }
    },

    COACH_PLAYER_TRAIN {
        /**
         * Conducts to {@code CoachPlayerTrainCommand} class.
         * @return  an instance of {@code CoachPlayerTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachPlayerTrainCommand();
        }
    },

    COACH_DO_PLAYER_TRAIN {
        /**
         * Conducts to {@code CoachDoPlayerTrainCommand} class.
         * @return  an instance of {@code CoachDoPlayerTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachDoPlayerTrainCommand();
        }
    },

    COACH_TACTIC_TRAIN {
        /**
         * Conducts to {@code CoachTacticTrainCommand} class.
         * @return  an instance of {@code CoachTacticTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachTacticTrainCommand();
        }
    },

    COACH_DO_TACTIC_TRAIN {
        /**
         * Conducts to {@code CoachDoTacticTrainCommand} class.
         * @return  an instance of {@code CoachDoTacticTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachDoTacticTrainCommand();
        }
    },

    COACH_START {
        /**
         * Conducts to {@code CoachStartCommand} class.
         * @return  an instance of {@code CoachStartCommand} class.
         */
        @Override
        public Command getCommand() {
            return new CoachStartCommand();
        }
    },

    PHYSIO_PLAYER_TRAIN {
        /**
         * Conducts to {@code PhysioPlayerTrainCommand} class.
         * @return  an instance of {@code PhysioPlayerTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PhysioPlayerTrainCommand();
        }
    },

    PHYSIO_DO_PLAYER_TRAIN {
        /**
         * Conducts to {@code PhysioDoPlayerTrainCommand} class.
         * @return  an instance of {@code PhysioDoPlayerTrainCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PhysioDoPlayerTrainCommand();
        }
    },

    PHYSIO_START {
        /**
         * Conducts to {@code PhysioStartCommand} class.
         * @return  an instance of {@code PhysioStartCommand} class.
         */
        @Override
        public Command getCommand() {
            return new PhysioStartCommand();
        }
    };

    /**
     * An abstract method that realizes inside each enumeration
     * element.
     * @return  an instance of definite command class.
     */
    public abstract Command getCommand();
}
