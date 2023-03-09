import { ComponentStory, ComponentMeta } from '@storybook/react';
import ChatConversation, {IChatConversation} from './ChatConversation';
import { mockBaseTemplateProps } from './ChatConversation.mocks';

export default {
  title: 'cards/ChatConversation',
  component: ChatConversation,
  // More on argTypes: https://storybook.js.org/docs/react/api/argtypes
  argTypes: {},
} as ComponentMeta<typeof ChatConversation>;

// More on component templates: https://storybook.js.org/docs/react/writing-stories/introduction#using-args
const Template: ComponentStory<typeof ChatConversation> = (args) => (
  <ChatConversation {...args} />
);

export const Base = Template.bind({});
// More on args: https://storybook.js.org/docs/react/writing-stories/args

Base.args = {
  ...mockBaseTemplateProps.base,
} as IChatConversation;

