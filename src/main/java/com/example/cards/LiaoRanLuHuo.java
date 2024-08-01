// package com.example.cards;

// import com.example.actions.DecreaseMonsterMaxHealthAction;
// import com.example.actions.EachBurnAction;
// import com.example.enums.AbstractCardEnum;
// import com.example.helpers.ModHelper;
// import com.example.power.RiShiPower;
// import com.megacrit.cardcrawl.actions.AbstractGameAction;
// import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
// import com.megacrit.cardcrawl.actions.common.DamageAction;
// import com.megacrit.cardcrawl.actions.common.DrawCardAction;
// import com.megacrit.cardcrawl.actions.common.GainBlockAction;
// import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
// import com.megacrit.cardcrawl.cards.AbstractCard;
// import com.megacrit.cardcrawl.cards.DamageInfo;
// import com.megacrit.cardcrawl.cards.status.Burn;
// import com.megacrit.cardcrawl.characters.AbstractPlayer;
// import com.megacrit.cardcrawl.core.CardCrawlGame;
// import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
// import com.megacrit.cardcrawl.localization.CardStrings;
// import com.megacrit.cardcrawl.monsters.AbstractMonster;

// import basemod.abstracts.CustomCard;

// public class LiaoRanLuHuo extends CustomCard {

//     public static final String ID = ModHelper.MakePath(LiaoRanLuHuo.class.getSimpleName());
//     private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
//     private static final String NAME = cardStrings.NAME;
//     private static final String DESCRIPTION = cardStrings.DESCRIPTION;
//     private static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
//     private static final String IMG_PATH = "cthughaResources/img/card/017.png";
//     private static final int COST = 4;
//     private static final CardType TYPE = CardType.SKILL;
//     private static final CardColor COLOR = AbstractCardEnum.MOD_NAME_COLOR;;
//     private static final CardRarity RARITY = CardRarity.UNCOMMON;
//     private static final CardTarget TARGET = CardTarget.ENEMY;

//     private int rawCost = COST;

//     public LiaoRanLuHuo() {
//         super(ID, NAME, IMG_PATH, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
//     }

//     @Override
//     public void upgrade() {
//         if (!this.upgraded) {
//             this.upgradeName();
//             this.rawDescription = UPGRADE_DESCRIPTION;
//             this.initializeDescription();

//             this.upgradeBaseCost(3);
//             rawCost = this.cost;
//         }
//     }

//     @Override
//     public void use(AbstractPlayer p, AbstractMonster m) {
//         this.addToBot(new DecreaseMonsterMaxHealthAction(m, 36));
//     }

//     public void applyPowers() {
//         super.applyPowers();
//         this.calcAndSetCost(this);
//     }

//     private void calcAndSetCost(LiaoRanLuHuo _inst) {
//         this.addToBot(new AbstractGameAction() {
//             public void update() {
//                 int count = 0;
//                 for (AbstractMonster monster : AbstractDungeon.getMonsters().monsters) {
//                     if (!monster.isDeadOrEscaped()) {
//                         count++;
//                     }
//                 }
//                 int curCost = _inst.rawCost - count;
//                 if (curCost < 0) {
//                     curCost = 0;
//                 }
//                 _inst.upgradeBaseCost(curCost);

//                 this.isDone = true;
//             }
//         });
//     }

// }
