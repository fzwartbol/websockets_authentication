import { IChatConversation } from './ChatConversation';

const base: IChatConversation = {
messages: [
  {
    id: "1",
    messageContent: 'Hello world!',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '1',
      username: 'frederikzwartbol@gmail.com',
      firstName: 'Frederik',
      lastName: 'Zwartbol',
      profileImage: "/profileImage/Frederik.jpg"
    }
  },
  {
    id: "2",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
  {
    id: "3",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
  {
    id: "4",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
  {
    id: "5",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
  {
    id: "6",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
  {
    id: "7",
    messageContent: 'What is going on on here',
    sendDate: 'now',
    conversationId: '1',
    user: {
      id: '2',
      username: 'elonmusk@twitter.com',
      firstName: 'Elon',
      lastName: 'Musk',
      profileImage: "/profileImage/elon.jpg"
    }
  },
]
};

export const mockBaseTemplateProps = {
  base,
};
