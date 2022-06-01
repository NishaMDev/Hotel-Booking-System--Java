import React, {useEffect} from "react";
import PaypalExpressBtn from "react-paypal-express-checkout";
import {PayPalButtons, PayPalScriptProvider, usePayPalScriptReducer} from "@paypal/react-paypal-js";

export class PayPal extends React.Component {
    render() {

        const success=async () => {
            await this.props.onSuccess()
        }
// This values are the props in the UI
         const amount = this.props.amount ;
        const currency = "USD";
        const style = {"layout":"vertical"};
// Custom component to wrap the PayPalButtons and handle currency changes
        const ButtonWrapper = ({ currency, showSpinner }) => {
            // usePayPalScriptReducer can be use only inside children of PayPalScriptProviders
            // This is the main reason to wrap the PayPalButtons in a new component
            const [{ options, isPending }, dispatch] = usePayPalScriptReducer();

            useEffect(() => {
                dispatch({
                    type: "resetOptions",
                    value: {
                        ...options,
                        currency: currency,
                    },
                });
            }, [currency, showSpinner]);


            return (<>
                    { (showSpinner && isPending) && <div className="spinner" /> }
                    <PayPalButtons
                        style={style}
                        disabled={false}
                        forceReRender={[amount, currency, style]}
                        fundingSource={undefined}
                        createOrder={(data, actions) => {
                            return actions.order
                                .create({
                                    purchase_units: [
                                        {
                                            amount: {
                                                currency_code: currency,
                                                value: amount,
                                            },
                                        },
                                    ],
                                })
                                .then((orderId) => {
                                    // Your code here after create the order
                                    return orderId;
                                });
                        }}
                        onApprove={function (data, actions) {

                            return actions.order.capture().then(async function () {
                                console.log('testsuccesss')
                                await success()
                            });
                        }}
                    />
                </>
            );
        }

        return (
            <PayPalScriptProvider
                options={{
                    "client-id": "AUJw5sNMCBMLAw4_C0kl_dmtXTcR8x-l0TgIUahx1X0yoTXk92GdrL-LzzD4N3eOK-gpnNeLpbFs8q40",
                    components: "buttons",
                    currency: "USD"
                }}
            >
                <ButtonWrapper
                    currency={currency}
                    showSpinner={false}
                />
            </PayPalScriptProvider>
        );
    }
}
