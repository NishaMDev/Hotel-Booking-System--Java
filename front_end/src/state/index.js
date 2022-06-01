import { atom, selector } from "recoil";

export const hotelListStateAtom = atom({
    key: "hotelListStateAtom", // unique ID
    default: "", // default value
});
export const currentStateAtom = atom({
    key: "currentStateAtom", // unique ID
    default: "", // default value
});

