export class CardSet {
    name?: string;
    code?: string;
    cardCount?: number;
    iconSVGURI?: string;
}

export class Card {
    scryfallId?: string;
    oracleId?: string;
    name?: string;
    set?: string;
    collectorNumber?: string;
    typeLine?: string;
    manaCost?: string;
    cmc?: string;
    colors?: string;
    colorIdentity?: string;
    scryfallUri?: string;
}