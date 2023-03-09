import ChatMessage, {IChatMessage} from "../message/ChatMessage";

export interface IChatConversation {
    messages: IChatMessage[]
}

const ChatConversation: React.FC<IChatConversation> = ({messages}) => {

  return (
      <div className={"max-w-lg"}>
          <div className={"overflow-y-auto"}>
              {messages.map( message => <ChatMessage id={message.id} messageContent={message.messageContent} sendDate={message.sendDate} conversationId={message.conversationId} user={message.user} />)}
          </div>
      </div>
)};

export default ChatConversation;
